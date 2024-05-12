package com.nhomA.mockproject.service.impl;

import com.nhomA.mockproject.dto.PaymentRequest;
import com.nhomA.mockproject.service.PaypalService;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaypalServiceImpl implements PaypalService {
    @Value("${paypal.clientId}")
    private String clientId;

    @Value("${paypal.clientSecret}")
    private String clientSecret;
    @Override
    public String createPayment(PaymentRequest paymentRequest) throws PayPalRESTException {
        // Thiết lập thông tin thanh toán
        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(paymentRequest.getAmount());

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription(paymentRequest.getDescription());

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:8080/api/paypal/complete-payment");
        redirectUrls.setReturnUrl("http://localhost:8080/api/paypal/cancel-payment");
        payment.setRedirectUrls(redirectUrls);

        // Tạo thanh toán và lấy link phê duyệt
        APIContext apiContext = new APIContext(clientId, clientSecret, "sandbox");
        Payment createdPayment = payment.create(apiContext);
        return createdPayment.getLinks().stream()
                .filter(link -> "approval_url".equals(link.getRel().toLowerCase()))
                .findFirst()
                .map(Links::getHref)
                .orElseThrow(() -> new RuntimeException("No approval_url in response"));
    }

    @Override
    public String completePayment(String paymentId, String payerId) throws PayPalRESTException {
        // Thực hiện các bước để hoàn tất thanh toán và lấy trạng thái thanh toán
        APIContext apiContext = new APIContext(clientId, clientSecret, "sandbox");
        Payment payment = new Payment();
        payment.setId(paymentId);

        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);

        Payment executedPayment = payment.execute(apiContext, paymentExecution);
        return executedPayment.getState();
    }
}
