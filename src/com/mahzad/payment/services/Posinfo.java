package com.mahzad.payment.services;

import com.mahzad.payment.contract.Pos;
import com.mahzad.payment.enums.Menu;

import java.time.LocalDate;

public class Posinfo implements Pos {
    public String cardNumber;
    public LocalDate withdrawalDate;
    public final String bankAccountNumber = "123";
    public String posCode;
    public Menu selectedMenu;
    public Posinfo(String cardNumber, LocalDate withdrawalDate, String posCode) {
        this.cardNumber = cardNumber;
        this.withdrawalDate = withdrawalDate;
        this.posCode = posCode;
    }

    @Override
    public String getPosOwnerAccountNumber() {
        //سرویس دریافت شماره حساب
        return "456";
    }
}
