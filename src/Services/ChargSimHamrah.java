package Services;

import Contract.Systemic;
import Enums.SimOperator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ChargSimHamrah extends SystemicPayment implements Systemic {
    private boolean isDirect;
    private SimOperator simOperator;
    public static final Map<String, String> amountDictionary = new HashMap<>();

    static {
        amountDictionary.put("1", "1000 Rial");
        amountDictionary.put("2", "2000 Rial");
        amountDictionary.put("3", "3000 Rial");
    }

    public ChargSimHamrah(PaymentSms paymentSms , Posinfo posinfo, String phoneNumber, boolean isDirect, SimOperator simOperator) {
        super(paymentSms, posinfo);
        this.isDirect = isDirect;
        this.simOperator = simOperator;
    }

    @Override
    public void processPayment() {
        getPaymentInfo();
        getSelectedAmount();
        super.calculateFee();
        if(super.payConfirmation("\nThe operator is: " + simOperator.getOperatorName() + " \n" +
                "The amount is: "+ amount+ " Rial")) {
            super.processPayment();
            sendPaymentResult();
        }
        super.backToMainMenu();
    }

    private void getSelectedAmount()
    {
        System.out.println("\nPlease return you desired amount code: ");

        for(Map.Entry<String, String> entry : amountDictionary.entrySet())
            System.out.println(entry.getKey() + ": " + entry.getValue());

        Scanner scanner = new Scanner(System.in);
        String selectedAmount = scanner.nextLine();

        String selectedAmountValue = amountDictionary.get(selectedAmount);
        super.amount = Double.parseDouble(selectedAmountValue.substring(0, selectedAmountValue.length() - 1));
    }

    @Override
    public boolean getPaymentInfo() {
        //بواسطه اطلاعات اپراتور
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
