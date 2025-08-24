package com.example.payment_instruction_service.service;

import com.example.payment_instruction_service.model.PaymentInstruction;
import com.example.payment_instruction_service.model.PaymentRequest;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                savedInstruction.getCreated()
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
                        pi.getCreated()
                )).toList()
        );
    }



    class PaymentResponse {
        private UUID id;
        private String status;
        private LocalDateTime createdAt;

        public PaymentResponse(UUID id, String status, LocalDateTime createdAt) {
            this.id = id;
            this.status = status;
            this.createdAt = createdAt;
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

        public LocalDateTime getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
        }
    }
}
