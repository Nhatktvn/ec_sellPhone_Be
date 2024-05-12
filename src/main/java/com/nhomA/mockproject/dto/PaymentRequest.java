package com.nhomA.mockproject.dto;

import com.paypal.api.payments.Amount;

public class PaymentRequest {
    private String amount;
    private String description;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
