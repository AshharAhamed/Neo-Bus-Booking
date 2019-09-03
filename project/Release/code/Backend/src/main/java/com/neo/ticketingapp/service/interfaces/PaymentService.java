package com.neo.ticketingapp.service.interfaces;

public interface PaymentService {
    public boolean processPayment(String paymentCardNo, String ccNo, double amount);
}
