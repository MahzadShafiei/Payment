package com.mahzad.payment.services;

public class ReportModel {
    public String action;
    public String result;
    public String bank;
    public String date;
    public String pan;
    public String amount;

    public ReportModel(String action, String result, String bank, String date,  String pan, String amount) {
        this.action = action;
        this.result = result;
        this.bank = bank;
        this.date = date;
        this.pan = pan;
        this.amount = amount;
    }
    public ReportModel() {

    }
}
