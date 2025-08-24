package com.example.payment_instruction_service.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentEvent(
        String paymentId,
        String debtorAccount,
        String creditorAccount,
        BigDecimal amount,
        String currency,
        String reference,
        LocalDateTime timestamp
) {}