package Services;

import Contract.fee;
import Enums.Menu;

public class Buy extends Payment implements fee{

    private final double amount;
    private Menu menu;
    private final Posinfo posinfo;

    public Buy(PaymentSms paymentSms,Posinfo posinfo, double amount) {
        super(paymentSms, posinfo);
        this.amount = amount;
        this.posinfo = posinfo;
    }

    @Override
    public void processPayment() {
        super.calculateFee();
        double balance = getBalance();
        String posOwnerAccountNumber = this.posinfo.getPosOwnerAccountNumber();

        if(super.payConfirmation(""))
        {
            if (balance >= (fee + amount)) {
                super.transfer(posOwnerAccountNumber, cardNumber, amount);
                super.transfer(bankAccountNumber, cardNumber, fee);
                super.printReceipt(amount + fee);
            } else
                super.sendSMS("Not enough money");
        }

        super.backToMainMenu();
    }
}
