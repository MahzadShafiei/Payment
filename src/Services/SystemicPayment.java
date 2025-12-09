package Services;

import Contract.fee;

public abstract class SystemicPayment extends Payment implements fee {

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
            super.printReceipt(amount + fee);
        }
        else
            super.sendSMS( "Not enough money");

    }

}