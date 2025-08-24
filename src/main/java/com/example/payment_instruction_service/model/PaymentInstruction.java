package com.example.payment_instruction_service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class PaymentInstruction {

    @Id
    @GeneratedValue
    UUID id;

    String debtorAccount;
    String creditorAccount;
    BigDecimal amount;
    String currency;
    String reference;
    String created;
    Status status;

    public enum Status {
        PENDING,
        ERROR
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PaymentInstruction{" +
                "id=" + id +
                ", debtorAccount='" + debtorAccount + '\'' +
                ", creditorAccount='" + creditorAccount + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", reference='" + reference + '\'' +
                ", created=" + created +
                ", status=" + status +
                '}';
    }
}
