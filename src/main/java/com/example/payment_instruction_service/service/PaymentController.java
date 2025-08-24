package com.example.payment_instruction_service.service;

import com.example.payment_instruction_service.model.PaymentInstruction;
import com.example.payment_instruction_service.model.PaymentRequest;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
public class PaymentController {

    PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/api/payment")
    public ResponseEntity<PaymentResponse> createPayment(@Valid @RequestBody PaymentRequest paymentRequest) {
        PaymentInstruction savedInstruction = paymentService.createPayment(paymentRequest);

        PaymentResponse response = new PaymentResponse(
                savedInstruction.getId(),
                savedInstruction.getStatus().name(),
                savedInstruction.getCreated(),
                savedInstruction.getDebtorAccount(),
                savedInstruction.getCreditorAccount(),
                savedInstruction.getAmount(),
                savedInstruction.getCurrency(),
                savedInstruction.getReference()
        );

        return ResponseEntity.status(201).body(response);

    }

    @GetMapping("/api/fetchAllPayments")
    public ResponseEntity<List<PaymentResponse>> fetchAllPayments() {
        List<PaymentInstruction> paymentInstruction = paymentService.getAllPayments();
        return ResponseEntity.ok(
                paymentInstruction.stream().map(pi -> new PaymentResponse(
                        pi.getId(),
                        pi.getStatus().name(),
                        pi.getCreated(),
                        pi.getDebtorAccount(),
                        pi.getCreditorAccount(),
                        pi.getAmount(),
                        pi.getCurrency(),
                        pi.getReference()
                )).toList()
        );
    }



    class PaymentResponse {
        private UUID id;
        private String status;
        private String createdAt;
        private String debtorAccount;
        private String creditorAccount;
        private BigDecimal amount;
        private String currency;
        private String reference;

        public PaymentResponse(
                UUID id,
                String status,
                String createdAt,
                String debtorAccount,
                String creditorAccount,
                BigDecimal amount,
                String currency,
                String reference) {
            this.id = id;
            this.status = status;
            this.createdAt = createdAt;
            this.debtorAccount = debtorAccount;
            this.creditorAccount = creditorAccount;
            this.amount = amount;
            this.currency = currency;
            this.reference = reference;
        }

        public UUID getId() {
            return id;
        }

        public void setId(UUID id) {
            this.id = id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDebtorAccount() {
            return debtorAccount;
        }

        public void setDebtorAccount(String debtorAccount) {
            this.debtorAccount = debtorAccount;
        }

        public String getCreditorAccount() {
            return creditorAccount;
        }

        public void setCreditorAccount(String creditorAccount) {
            this.creditorAccount = creditorAccount;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getReference() {
            return reference;
        }

        public void setReference(String reference) {
            this.reference = reference;
        }
    }
}
