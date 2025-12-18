package com.mahzad.payment.contract;

public interface Sms {
     void  sendSMS(String smsMessage);
     String getPhoneNumber();
}
