package com.mahzad.payment.services;

import com.mahzad.payment.contract.Fee;
import com.mahzad.payment.contract.Systemic;
import com.mahzad.payment.enums.Organiztion;
import common.ConfigLoadException;

import java.util.Scanner;

public class Bill extends SystemicPayment implements Fee, Systemic {

    Scanner scanner = new Scanner(System.in);
    final String billId;
    final String paymentId;
    String billDocumentCode;
    String billOrganizationsCode;
    String billServiceCode;
    String billControlCode;
    String paymentAmount;
    String paymentYear;
    String paymentPeriod;
    String paymentControl1;
    String paymentControl2;
    public Bill(PaymentSms paymentSms , Posinfo posinfo, String billId, String paymentId) {
        super(paymentSms, posinfo);
        this.paymentId = paymentId;
        this.billId = billId;
    }

    @Override
    public boolean getPaymentInfo() {
        //دریافت اطلاعات بواسطه شناسه پرداخت و شناسه قبض
        billDocumentCode = billId.substring(0,billId.length()-5);
        billOrganizationsCode = billId.substring(billDocumentCode.length(),billDocumentCode.length()+3);
        billServiceCode = billId.substring(billDocumentCode.length()+3, billDocumentCode.length()+4);
        billControlCode = billId.substring(billDocumentCode.length()+4);

        paymentAmount = paymentId.substring(0,paymentId.length()-5);
        paymentYear = paymentId.substring(paymentAmount.length(),paymentAmount.length()+1);
        paymentPeriod = paymentId.substring(paymentYear.length()+1, paymentYear.length()+3);
        paymentControl1 = paymentId.substring(paymentPeriod.length()+3, paymentPeriod.length()+4);
        paymentControl2 = paymentId.substring(paymentControl1.length()+4);

        recieveAccountNumber = "147";
        amount = Double.parseDouble(paymentAmount + "000");
        return super.payConfirmation("\nThe bill organization: " + Organiztion.fromCode(Integer.parseInt(billServiceCode))+
                "\nThe bill amount is: " + amount+ " Rial"+
                "\nThe year: 140" + paymentYear+
                "\nThe period: " + paymentPeriod);
    }

    @Override
    public void processPayment() {
        try {
            super.calculateFee();
            if (getPaymentInfo()) {
                super.processPayment();
            }
            sendPaymentResult();
            super.followingProcess(amount + fee);
        }
        catch (Exception e) {
            throw new ConfigLoadException("Failed to process bill payment", e);
        }
    }

    @Override
    public void sendPaymentResult() {
        //ارسال اطلاعات پرداخت به سازمان صادر کننده قیض
    }
}
