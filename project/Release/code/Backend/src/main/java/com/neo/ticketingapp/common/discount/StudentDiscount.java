package com.neo.ticketingapp.common.discount;

import com.neo.ticketingapp.common.constants.CommonConstants;
import com.neo.ticketingapp.common.discount.interfaces.Discount;

public class StudentDiscount extends DiscountDecorator {

    public StudentDiscount(Discount discount) {
        super(discount);
    }

    @Override
    public Double handleDiscount(Double originalPrice) {
        Double discountedPrice = super.handleDiscount(originalPrice);
        if (discountedPrice > CommonConstants.STUDENT_DISCOUNT)
            discountedPrice -= CommonConstants.STUDENT_DISCOUNT;
        return discountedPrice;
    }
}
