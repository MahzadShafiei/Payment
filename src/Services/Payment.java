package Services;

import java.time.LocalDate;
import Contract.Pos;
import Contract.Sms;
import Contract.fee;
import Enums.Menu;

public abstract class Payment implements fee{
    protected final Pos  pos;
    protected final Sms sms;
    protected double fee;
    protected String cardNumber;
    protected LocalDate withdrawalDate;
    protected double amount;
    protected final String bankAccountNumber;
    protected final Menu selectedMenu;

    public Payment(Pos pos, Sms sms, String cardNumber, LocalDate withdrawalDate, double amount, Menu selectedMenu)
    {
        this.sms = sms;
        this.pos = pos;
        this.bankAccountNumber = "123";
        this.cardNumber = cardNumber;
        this.withdrawalDate = withdrawalDate;
        this.amount = amount;
        this.selectedMenu = selectedMenu;
    }

    @Override
    public void calculateFee() {
        this.fee = 200;
    }

    public abstract boolean processPayment();

    public String getPosOwnerAccountNumber()
    {
        return pos.getPosOwnerAccountNumber();
    }

    protected void  sendSMS(String message)
    {
        String phoneNumber = this.sms.getPhoneNumber(cardNumber);
        this.sms.sendSMS(message, phoneNumber);
    }

    protected  double getBalance()
    {
        //by cardNumber from a service
        return 1000;
    }

    protected void transfer(String destinationAccount, String  cardNumber, double amount)
    {
        //جابه جایی وجه
    }

    protected void printReceipt(double withdrawalAmount)
    {
        System.out.println("Receipt: Withdrawal " + withdrawalAmount + "$ by cardNumber: " + cardNumber + "---------------------" + withdrawalDate );
    }
}
