package com.example.payment_instruction_service.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class PaymentServiceTest {

    PaymentService paymentService = new PaymentService(null, null);

    @Test
    void returns_iban_not_valid_when_iban_empty() {
        assertFalse(paymentService.isValidIBAN(""));
    }

    @Test
    void returns_iban_not_valid_when_iban_null() {
        assertFalse(paymentService.isValidIBAN(null));
    }

    @Test
    void returns_iban_not_valid_when_iban_too_short() {
        assertFalse(paymentService.isValidIBAN("DE1"));
    }

    @Test
    void returns_iban_not_valid_when_iban_contains_non_special_characters() {
        assertFalse(paymentService.isValidIBAN("GB82WEST123/5698765432"));
    }

    @Test
    void returns_iban_valid_when_iban_correct() {
        assertTrue(paymentService.isValidIBAN("GB82WEST12345698765432"));
    }
}