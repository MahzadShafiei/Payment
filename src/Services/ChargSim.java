package Services;

import java.time.LocalDate;
import Contract.Pos;
import Contract.Sms;
import Contract.fee;
import Enums.Menu;

public abstract class ChargSim extends Payment implements fee{

    protected String recieveAccountNumber;
    public ChargSim(Pos pos, Sms sms, String cardNumber, LocalDate withdrawalDate, double amount, Menu selectedMenu) {
        super(pos, sms, cardNumber, withdrawalDate, amount, selectedMenu);
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