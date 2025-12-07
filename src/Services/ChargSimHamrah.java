package Services;

import Contract.Sms;
import Contract.Systemic;
import Enums.SimOperator;

public class ChargSimHamrah extends SystemicPayment implements Systemic {
    private boolean isDirect;
    private SimOperator simOperator;
    public ChargSimHamrah(Sms sms, Posinfo posinfo, double amount, String phoneNumber, boolean isDirect, SimOperator simOperator) {
        super(sms, posinfo);
        super.amount = amount;
        this.isDirect = isDirect;
        this.simOperator = simOperator;
    }

    @Override
    public void processPayment() {
        getPaymentInfo();
        super.processPayment();
        sendPaymentResult();
        super.backToMainMenu();
    }

    @Override
    public boolean getPaymentInfo() {
        recieveAccountNumber = "789";
        return  true;
    }

    @Override
    public void sendPaymentResult() {
        //ارسال شماره موبایل به اپراتور
        if(!isDirect)
            System.out.println("Pin: ****");
    }
}
