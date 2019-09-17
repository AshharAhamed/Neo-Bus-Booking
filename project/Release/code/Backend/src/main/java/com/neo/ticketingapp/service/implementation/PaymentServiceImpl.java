package com.neo.ticketingapp.service.implementation;

import com.neo.ticketingapp.common.discount.ForeignCustomer;
import com.neo.ticketingapp.common.discount.DisabledDiscount;
import com.neo.ticketingapp.common.discount.LocalCustomer;
import com.neo.ticketingapp.common.discount.StudentDiscount;
import com.neo.ticketingapp.service.interfaces.PaymentService;

public class PaymentServiceImpl implements PaymentService {
    @Override
    public boolean validatePayment(String paymentCardNo, String ccNo, double amount) {
        return true;
    }

    @Override
    public Double processPayment(double amount, String type) {
        switch (type) {
            case "LOCAL_NEW":
                LocalCustomer localCustomer = new LocalCustomer();
                return localCustomer.handleDiscount(amount);
            case "LOCAL_NEW_DISABLED":
                DisabledDiscount disabledDiscount = new DisabledDiscount(new LocalCustomer());
                return disabledDiscount.handleDiscount(amount);
            case "LOCAL_NEW_STUDENT":
                StudentDiscount studentDiscount = new StudentDiscount(new LocalCustomer());
                return studentDiscount.handleDiscount(amount);
            case "FOREIGN_NEW":
                ForeignCustomer foreignCustomer = new ForeignCustomer();
                return foreignCustomer.handleDiscount(amount);
            case "FOREIGN_NEW_DISABLED":
                DisabledDiscount disabledDiscount1 = new DisabledDiscount(new ForeignCustomer());
                return disabledDiscount1.handleDiscount(amount);
            case "FOREIGN_NEW_STUDENT":
                StudentDiscount studentDiscount1 = new StudentDiscount(new ForeignCustomer());
                return studentDiscount1.handleDiscount(amount);
            default:
                return amount;
        }
    }
}
