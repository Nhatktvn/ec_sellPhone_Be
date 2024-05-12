package com.nhomA.mockproject.service;

import com.nhomA.mockproject.dto.PaymentRequest;
import com.paypal.base.rest.PayPalRESTException;

public interface PaypalService {
    String  createPayment (PaymentRequest paymentRequest) throws PayPalRESTException;
    String completePayment(String paymentId, String payerId) throws PayPalRESTException;
}
