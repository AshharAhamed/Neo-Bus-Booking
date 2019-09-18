package com.neo.ticketingapp.service.interfaces;

public interface PaymentService {
    boolean validatePayment(String paymentCardNo, String ccNo, double amount);
    Double processPayment(double amount, String type);
}
