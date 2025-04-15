package com.conference.strategy;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class CreditCardPaymentStrategy implements PaymentStrategy {
    @Override
    public boolean processPayment(String orderId, BigDecimal amount) {
        // In a real application, this would integrate with a payment gateway
        System.out.println("Processing credit card payment for order: " + orderId + " amount: " + amount);
        return true;
    }

    @Override
    public String getPaymentType() {
        return "CREDIT_CARD";
    }
} 