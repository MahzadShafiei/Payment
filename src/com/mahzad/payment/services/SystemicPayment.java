package com.mahzad.payment.services;

import com.mahzad.payment.contract.Fee;

public abstract class SystemicPayment extends Payment implements Fee {

    protected double amount;
    protected String recieveAccountNumber;

    public SystemicPayment(PaymentSms paymentSms ,Posinfo posinfo) {
        super(paymentSms, posinfo);
    }

    @Override
    public void processPayment() {
        super.calculateFee();
        double balance = getBalance();

        if(balance>=(fee+amount)) {
            super.transfer(recieveAccountNumber, cardNumber, amount);
            super.transfer(bankAccountNumber, cardNumber, fee);
        }
        else
            super.sendSMS( "Not enough money");

    }

}