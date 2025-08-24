package com.example.payment_instruction_service.repository;

import com.example.payment_instruction_service.model.PaymentInstruction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<PaymentInstruction, UUID> {
}
