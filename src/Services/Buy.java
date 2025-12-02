package Services;

import Contract.Pos;
import Contract.Sms;
import Contract.fee;
import Enums.Menu;

public class Buy extends Payment implements fee{

    private double amount;
    private Pos pos;
    private Menu menu;
    public Buy(Pos pos, Sms sms,Posinfo posinfo, double amount) {
        super(sms, posinfo);
        this.amount = amount;
        this.pos = pos;
    }

    @Override
    public boolean processPayment() {
        super.calculateFee();
        double balance = getBalance();
        String posOwnerAccountNumber = getPosOwnerAccountNumber();
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

    public String getPosOwnerAccountNumber()
    {
        return pos.getPosOwnerAccountNumber();
    }

}
