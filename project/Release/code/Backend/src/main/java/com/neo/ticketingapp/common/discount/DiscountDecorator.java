package com.neo.ticketingapp.common.discount;

import com.neo.ticketingapp.common.discount.interfaces.Discount;

public class DiscountDecorator implements Discount {

    protected Discount discount;

    public DiscountDecorator(Discount discount) {
        this.discount = discount;
    }

    @Override
    public Double handleDiscount(Double originalPrice) {
        return discount.handleDiscount(originalPrice);
    }
}
