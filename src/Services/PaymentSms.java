package Services;

import Contract.Sms;

public class PaymentSms implements Sms{
    public final String cardNumber;
    public String phoneNumber;

    public PaymentSms(String cardNumber){
        this.cardNumber=cardNumber;
    }

    @Override
    public void sendSMS(String smsMessage) {
        this.phoneNumber = this.getPhoneNumber();
        System.out.println("SMS: " + smsMessage + " to -> " +  phoneNumber);
    }

    @Override
    public String getPhoneNumber() {
        //by cardNumber from a service
        return ("0912");
    }
}
