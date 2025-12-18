package com.mahzad.payment.services;

import common.ConfigLoadException;

public class AccountBalance extends Payment{
    public AccountBalance(PaymentSms paymentSms,Posinfo posinfo) {
        super(paymentSms,posinfo);
    }

    @Override
    public void processPayment() {
        try {
            super.calculateFee();
            double balance = super.getBalance();

            if (super.payConfirmation("")) {
                if (balance >= super.fee) {
                    super.transfer(bankAccountNumber, cardNumber, super.fee);
                    super.printReceipt(super.fee);
                    super.sendSMS("Your balance is Rial" + balance);
                } else
                    super.sendSMS("Not enough money");
            }

            super.backToMainMenu();
        }
        catch (Exception e) {
            throw new ConfigLoadException("Failed to process account balance payment", e);
        }
    }
}
