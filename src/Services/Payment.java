package Services;

import java.time.LocalDate;
import Contract.Pos;
import Contract.Sms;
import Contract.fee;
import Enums.Menu;

public abstract class Payment implements fee{
    protected final Sms sms;
    protected double fee;
    protected String cardNumber;
    protected LocalDate withdrawalDate;
    protected final String bankAccountNumber;
    protected final Menu selectedMenu;

    public Payment(Sms sms, Posinfo posinfo)
    {
        this.sms = sms;
        this.bankAccountNumber = posinfo.bankAccountNumber;
        this.cardNumber = posinfo.cardNumber;
        this.withdrawalDate = posinfo.withdrawalDate;
        this.selectedMenu = posinfo.selectedMenu;
    }

    @Override
    public void calculateFee() {
        switch(selectedMenu)
        {
            case AccountBalance:
                this.fee = 10;
                break;

            case Buy:
                this.fee = 20;
                break;

            case Bill:
                this.fee = 30;
                break;

            case Charg:
                this.fee = 40;
                break;

            default :
                this.fee = 0;

        };
    }

    public abstract boolean processPayment();

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
