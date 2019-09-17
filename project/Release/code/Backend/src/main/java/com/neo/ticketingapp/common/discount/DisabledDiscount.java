package com.neo.ticketingapp.common.discount;

import com.neo.ticketingapp.common.discount.interfaces.Discount;

public class DisabledDiscount extends DiscountDecorator {

    private static final Double FIVE = 5d;

    public DisabledDiscount(Discount discount) {
        super(discount);
    }

    @Override
    public Double handleDiscount(Double originalPrice){
        Double discountedPrice = super.handleDiscount(originalPrice);
        if(discountedPrice > FIVE)
            discountedPrice -= FIVE;
        return discountedPrice;
    }
}
