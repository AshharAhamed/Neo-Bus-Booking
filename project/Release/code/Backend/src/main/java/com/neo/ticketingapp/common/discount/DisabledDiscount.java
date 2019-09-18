package com.neo.ticketingapp.common.discount;

import com.neo.ticketingapp.common.constants.CommonConstants;
import com.neo.ticketingapp.common.discount.interfaces.Discount;

public class DisabledDiscount extends DiscountDecorator {

    public DisabledDiscount(Discount discount) {
        super(discount);
    }

    @Override
    public Double handleDiscount(Double originalPrice) {
        Double discountedPrice = super.handleDiscount(originalPrice);
        if (discountedPrice > CommonConstants.DISABLED_DISCOUNT)
            discountedPrice -= CommonConstants.DISABLED_DISCOUNT;
        return discountedPrice;
    }
}
