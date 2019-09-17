package com.neo.ticketingapp.common.discount;

import com.neo.ticketingapp.common.discount.interfaces.Discount;

public class SeasonalDiscount extends DiscountDecorator {

    private static final Double NINETY_NINE = 99d;
    private static final Double ONE_HUNDRED = 100d;

    public SeasonalDiscount(Discount discount) {
        super(discount);
    }

    @Override
    public Double handleDiscount(Double originalPrice){
        Double discountedPrice = super.handleDiscount(originalPrice);
        return ((discountedPrice * NINETY_NINE) / ONE_HUNDRED);
    }

}
