package com.neo.ticketingapp.common.discount;

import com.neo.ticketingapp.common.constants.CommonConstants;
import com.neo.ticketingapp.common.discount.interfaces.Discount;

public class ForeignCustomer implements Discount {
    @Override
    public Double handleDiscount(Double originalPrice) {
        return ((originalPrice * CommonConstants.NEW_FOREIGNER_DISCOUNT) / CommonConstants.ONE_HUNDRED);
    }
}
