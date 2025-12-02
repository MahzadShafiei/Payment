package Services;

import Contract.Pos;
import Contract.Sms;
import Contract.fee;
import Contract.Systemic;
import Enums.Menu;

import java.time.LocalDate;

public class Bill extends SystemicPayment implements fee, Systemic {

    public Bill(Pos pos, Sms sms, String cardNumber, LocalDate withdrawalDate, Menu selectedMenu, double billId, double paymentId) {
        super(pos, sms, cardNumber, withdrawalDate, selectedMenu);
    }

    @Override
    public void getPaymentInfo() {
        //دریافت اطلاعات بواسطه شناسه پرداخت و شناسه قبض
        recieveAccountNumber = "147";
        amount = 500;
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
        //ارسال اطلاعات پرداخت به سازمان صادر کننده قیض
    }
}
