package com.company;

public enum PaymentType {
    CASH, CREDIT_CARD, VOUCHER, DEFAULT_CASH;

    public static PaymentType getPaymentType(String _paymentType)
    {
        for (PaymentType paymentType:
             PaymentType.values()) {
            if(paymentType.name().equals(_paymentType))
                return paymentType;
        }
        return DEFAULT_CASH;
    }
}
