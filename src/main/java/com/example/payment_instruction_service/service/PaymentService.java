package com.example.payment_instruction_service.service;

import com.example.payment_instruction_service.model.PaymentInstruction;
import com.example.payment_instruction_service.model.PaymentRequest;
import com.example.payment_instruction_service.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);
    private final PaymentRepository repository;
    private final PaymentProducer producer;

    public PaymentService(PaymentRepository repository, PaymentProducer producer) {
        this.repository = repository;
        this.producer = producer;
    }

    @Transactional
    public PaymentInstruction createPayment(PaymentRequest request) {
        PaymentInstruction instruction = new PaymentInstruction();
        instruction.setDebtorAccount(request.getDebtorAccount());
        instruction.setCreditorAccount(request.getCreditorAccount());
        instruction.setAmount(request.getAmount());
        instruction.setCurrency(request.getCurrency());
        instruction.setReference(request.getReference());
        instruction.setCreated(java.time.LocalDateTime.now().toString());
        if (isPaymentValid(request)) {
            instruction.setStatus(PaymentInstruction.Status.PENDING);
        } else {
            instruction.setStatus(PaymentInstruction.Status.ERROR);
        }
        // Here you would typically save the instruction to a database
        logger.info("Payment Instruction Created: " + instruction.getId() + " with status: " + instruction.getStatus());

        PaymentInstruction savedInstruction = repository.save(instruction);

        try {
            // Then publish event
            producer.send(savedInstruction);
        } catch (Exception e) {
            // Fallback: mark as ERROR but still persist the row
            savedInstruction.setStatus(PaymentInstruction.Status.ERROR);
            repository.save(savedInstruction);
        }

        return savedInstruction;
    }

    public List<PaymentInstruction> getAllPayments() {
        return repository.findAll();
    }

    public boolean isPaymentValid(PaymentRequest request) {
        return isValidIBAN(request.getCreditorAccount()) && isValidIBAN(request.getCreditorAccount()) && request.getCurrency().strip().equals("EUR");
    }

    public boolean isValidIBAN(String iban) {
        if (iban == null || iban.length() < 4  || !iban.matches("^[A-Z]{2}\\d{2}[A-Z0-9]+$")) {
            return false;
        }
        String reformatted = iban.substring(4) + iban.substring(0, 4);
        StringBuilder numericIBAN = new StringBuilder();
        for (char ch : reformatted.toUpperCase().toCharArray()) {
            if (Character.isDigit(ch)) {
                numericIBAN.append(ch);
            } else if (Character.isLetter(ch)) {
                numericIBAN.append((int) ch - 55); // A=10, B=11, ..., Z=35
            } else {
                return false;
            }
        }
        return true;
    }
}
