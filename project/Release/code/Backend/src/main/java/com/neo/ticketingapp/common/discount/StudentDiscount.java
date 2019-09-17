package com.neo.ticketingapp.common.discount;

import com.neo.ticketingapp.common.discount.interfaces.Discount;

public class StudentDiscount extends DiscountDecorator {

    private static final Double SIX = 6d;

    public StudentDiscount(Discount discount) {
        super(discount);
    }

    @Override
    public Double handleDiscount(Double originalPrice){
        Double discountedPrice = super.handleDiscount(originalPrice);
        if(discountedPrice > SIX)
            discountedPrice -= SIX;
        return discountedPrice;
    }
}
