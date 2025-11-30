package Services;

import java.time.LocalDate;
import Contract.Pos;
import Contract.Sms;
import Enums.Menu;

public class AccountBalance extends Payment{
    public AccountBalance(Pos pos, Sms sms, String cardNumber, LocalDate withdrawalDate, double amount, Menu selectedMenu) {
        super(pos, sms, cardNumber, withdrawalDate, amount, selectedMenu);
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
