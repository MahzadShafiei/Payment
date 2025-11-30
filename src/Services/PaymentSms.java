package Services;

import Contract.Sms;

public class PaymentSms implements Sms{
    @Override
    public void sendSMS(String smsMessage, String phoneNumber) {
        System.out.println("SMS: " + smsMessage + " to -> " +  phoneNumber);
    }

    @Override
    public String getPhoneNumber(String cardNumber) {
        //by cardNumber from a service
        return ("0912");
    }
}
