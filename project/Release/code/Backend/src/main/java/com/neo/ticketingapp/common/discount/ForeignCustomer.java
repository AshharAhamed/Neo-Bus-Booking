package com.neo.ticketingapp.common.discount;

import com.neo.ticketingapp.common.discount.interfaces.Discount;

public class ForeignCustomer implements Discount {

    private static final Double NINETY_EIGHT = 98d;
    private static final Double ONE_HUNDRED = 100d;

    @Override
    public Double handleDiscount(Double originalPrice) {
        return ((originalPrice * NINETY_EIGHT) / ONE_HUNDRED);
    }
}
