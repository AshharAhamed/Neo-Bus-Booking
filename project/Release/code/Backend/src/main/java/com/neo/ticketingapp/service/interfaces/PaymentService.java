package com.neo.ticketingapp.service.interfaces;

public interface PaymentService {
    boolean processPayment(String paymentCardNo, String ccNo, double amount);
}
