package Services;

import java.time.LocalDate;
import Contract.Pos;
import Contract.Sms;
import Contract.Systemic;
import Enums.Menu;

public class ChargSimHamrah extends SystemicPayment implements Systemic {
    private boolean isDirect;
    public ChargSimHamrah(Pos pos, Sms sms, String cardNumber, LocalDate withdrawalDate, double amount,  Menu selectedMenu, String phoneNumber, boolean isDirect) {
        super(pos, sms, cardNumber, withdrawalDate,   selectedMenu);
        super.amount = amount;
        this.isDirect = isDirect;
    }

    @Override
    public void getPaymentInfo() {
        recieveAccountNumber = "789";
    }

    @Override
    public boolean processPayment() {
        getPaymentInfo();
        super.processPayment();
        sendPaymentResult();
        return true;
    }

    @Override
    public void sendPaymentResult() {
        //ارسال شماره موبایل به اپراتور
        if(!isDirect)
            System.out.println("Pin: "+ 1234);
    }
}
