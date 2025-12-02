package Services;

import Contract.Sms;
import Contract.fee;

public abstract class SystemicPayment extends Payment implements fee {

    protected double amount;
    protected String recieveAccountNumber;
    public SystemicPayment(Sms sms,Posinfo posinfo) {
        super(sms, posinfo);
    }

    @Override
    public boolean processPayment() {
        super.calculateFee();
        double balance = getBalance();
        if(balance>=(fee+amount)) {
            super.transfer(recieveAccountNumber, cardNumber, amount);
            super.transfer(bankAccountNumber, cardNumber, fee);
            super.printReceipt(amount + fee);
            return true;
        }
        else {
            super.sendSMS( "Not enough money");
            return false;
        }
    }

}