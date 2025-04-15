package com.conference.strategy;

import java.math.BigDecimal;

public interface PaymentStrategy {
    boolean processPayment(String orderId, BigDecimal amount);
    String getPaymentType();
} 