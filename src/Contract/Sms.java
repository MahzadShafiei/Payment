package Contract;

public interface Sms {
     void  sendSMS(String smsMessage, String phoneNumber);
     String getPhoneNumber(String cardNumber);
}
