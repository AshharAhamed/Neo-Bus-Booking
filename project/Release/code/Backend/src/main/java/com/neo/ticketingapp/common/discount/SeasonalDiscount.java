package com.neo.ticketingapp.common.discount;

import com.neo.ticketingapp.common.constants.CommonConstants;
import com.neo.ticketingapp.common.discount.interfaces.Discount;

public class SeasonalDiscount extends DiscountDecorator {

    public SeasonalDiscount(Discount discount) {
        super(discount);
    }

    @Override
    public Double handleDiscount(Double originalPrice) {
        Double discountedPrice = super.handleDiscount(originalPrice);
        return ((discountedPrice * CommonConstants.SEASONAL_DISCOUNT) / CommonConstants.ONE_HUNDRED);
    }

}
