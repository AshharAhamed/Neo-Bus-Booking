package com.neo.ticketingapp.common.discount;

import com.neo.ticketingapp.common.discount.interfaces.Discount;

public class LocalCustomer implements Discount {

    private static final Double NINETY_SEVEN = 97d;
    private static final Double ONE_HUNDRED = 100d;

    @Override
    public Double handleDiscount(Double originalPrice) {
        return ((originalPrice * NINETY_SEVEN) / ONE_HUNDRED);
    }
}
