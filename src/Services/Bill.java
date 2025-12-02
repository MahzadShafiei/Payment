package Services;

import Contract.Sms;
import Contract.fee;
import Contract.Systemic;

public class Bill extends SystemicPayment implements fee, Systemic {

    public Bill(Sms sms, Posinfo posinfo, double billId, double paymentId) {
        super(sms, posinfo);
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
