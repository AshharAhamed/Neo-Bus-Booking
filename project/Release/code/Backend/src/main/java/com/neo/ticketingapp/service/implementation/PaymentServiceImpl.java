package com.neo.ticketingapp.service.implementation;

import com.neo.ticketingapp.service.interfaces.PaymentService;

public class PaymentServiceImpl implements PaymentService {
    @Override
    public boolean processPayment(String paymentCardNo, String ccNo, double amount) {
        return true;
    }
}
