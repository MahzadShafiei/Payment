package Services;

import java.time.LocalDate;
import Contract.Pos;
import Contract.Sms;
import Contract.fee;
import Enums.Menu;

public class Buy extends Payment implements fee{

    private double amount;
    public Buy(Pos pos, Sms sms, String cardNumber, LocalDate withdrawalDate, double amount, Menu selectedMenu) {
        super(pos, sms, cardNumber, withdrawalDate, selectedMenu);
        this.amount = amount;
    }

    @Override
    public boolean processPayment() {
        super.calculateFee();
        double balance = getBalance();
        String posOwnerAccountNumber = super.getPosOwnerAccountNumber();
        if(balance >= (fee + amount)) {
            super.transfer(posOwnerAccountNumber, cardNumber, amount);
            super.transfer(bankAccountNumber, cardNumber, fee);
            super.printReceipt(amount + fee);
            return true;
        }
        else  {
            super.sendSMS("Not enough money");
            return false;
        }
    }

}
