package com.hasanatasoy.shoppingcart.domain.order.payment;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Payment {

    private String creditCardNumber;
    private Date expirationDate;
    private int cvv;
    private PaymentType paymentType;
    private boolean isPaymentOkay;
}
