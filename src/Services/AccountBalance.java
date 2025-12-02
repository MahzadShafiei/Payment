package Services;

import Contract.Sms;

public class AccountBalance extends Payment{
    public AccountBalance(Sms sms,Posinfo posinfo) {
        super(sms,posinfo);
    }

    @Override
    public boolean processPayment() {
        super.calculateFee();
        double balance = getBalance();
        if(balance >= super.fee) {
            super.transfer(bankAccountNumber, cardNumber, super.fee);
            super.printReceipt(super.fee);
            super.sendSMS("Your balance is $" + balance);
            return true;
        }
        else
        {
            super.sendSMS("Not enough money");
            return false;
        }
    }
}
