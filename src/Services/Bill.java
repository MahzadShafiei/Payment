package Services;

import Contract.Sms;
import Contract.fee;
import Contract.Systemic;

import java.util.Scanner;

public class Bill extends SystemicPayment implements fee, Systemic {

    Scanner scanner = new Scanner(System.in);
    public Bill(Sms sms, Posinfo posinfo, double billId, double paymentId) {
        super(sms, posinfo);
    }

    @Override
    public boolean getPaymentInfo() {
        //دریافت اطلاعات بواسطه شناسه پرداخت و شناسه قبض
        recieveAccountNumber = "147";
        amount = 500;
        System.out.println("The bill organization: X \n" +
                "The bill amount is: "+ amount+ "$\n" +
                "Do you want to continue? (press y to continue)");
        String billConfirmation = scanner.nextLine();
        return billConfirmation.equals("y");
    }

    @Override
    public void processPayment() {
        if(getPaymentInfo()) {
            sendPaymentResult();
            super.processPayment();
        }
        super.backToMainMenu();
    }

    @Override
    public void sendPaymentResult() {
        //ارسال اطلاعات پرداخت به سازمان صادر کننده قیض
    }
}
