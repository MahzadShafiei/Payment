package Services;

import Enums.Menu;
import Enums.SimOperator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;
import java.awt.*;

public class MainMenu {

    Scanner scanner = new Scanner(System.in);
    Posinfo posinfo;
    PaymentSms paymentSms;
    Payment payment;

    public void start()
    {
        for (Menu menu : Menu.values())
            System.out.println(menu.getMenuCode() + ". " + menu.getMenuName());

        System.out.println("Please return you desired menu code: ");
        int selectedMenuCode = Integer.parseInt(scanner.nextLine());

        getPosInfo();

        Menu selectedMenu = Menu.fromCode(selectedMenuCode);
        posinfo.selectedMenu = selectedMenu;
        System.out.println("\n************    " + selectedMenu.getMenuName() + "    ************");

        switch(selectedMenu)
        {
            case AccountBalance:
                accountBalanceProccess();
                break;

            case Buy:
                buyProccess();
                break;

            case Bill:
                billProccess();
                break;

            case Charg:
                chargeProccess();
                break;

            default :
                payment = null;

        };

        payment.processPayment();

    }

    private void accountBalanceProccess()
    {
        payment = new AccountBalance(paymentSms, posinfo);
    }

    private void buyProccess()
    {
        System.out.println("Please enter the price: ");
        double price = Double.parseDouble(scanner.nextLine());
        if(price <= 0)
            throw new IllegalArgumentException("Price is out of range");
        payment = new Buy(paymentSms, posinfo, price);
    }

    private void billProccess()
    {
        int counter = 0;
        String billId;
        do {
            if(counter>2)
                throw new IllegalArgumentException("Bill Id is not correct, try again another time.");
            counter++;

            System.out.println("Please enter the bill Id (6-13 numbers): ");
            billId = scanner.nextLine().trim();
        }
        while (billId.length()<6 || billId.length()>13);

        counter = 0;
        String paymentId;
        do {
            if(counter>2)
                throw new IllegalArgumentException("Payment Id is not correct, try again another time.");
            counter++;

            System.out.println("Please enter the payment Id (6-13 numbers): ");
            paymentId = scanner.nextLine().trim();
        }
        while (paymentId.length()<6 || paymentId.length()>13);

        payment = new Bill(paymentSms, posinfo, billId, paymentId);
    }

    private void chargeProccess()
    {
        System.out.println("1. Direct");
        System.out.println("2. Pin");
        int selectedChargeMenu = Integer.parseInt(scanner.nextLine());

        if(selectedChargeMenu < 1 || selectedChargeMenu > 2)
            throw new IllegalArgumentException("Charge Menu is not correct, try again another time.");

        boolean isDirectCharge = selectedChargeMenu == 1;

        System.out.println("\nPlease return your desired Operator: ");
        for (SimOperator simOperator : SimOperator.values())
            System.out.println(simOperator.getSimOperatorCode() + ". " + simOperator.getOperatorName());

        int selectedOperatorCode = Integer.parseInt(scanner.nextLine());
        SimOperator selectedOperator = SimOperator.fromCode(selectedOperatorCode);

        String phoneNumber = "";
        if(isDirectCharge)
        {
            int counter = 0;
            do {
                if(counter>2)
                    throw new IllegalArgumentException("Phone Number is not correct, try again another time.");
                counter++;

                System.out.print("\nPlease enter the phone number: ");
                phoneNumber = scanner.nextLine().trim();
            }
            while ((!phoneNumber.matches("\\d{11}"))|| !phoneNumber.startsWith("09"));
        }

        payment = new ChargSimHamrah(paymentSms, posinfo, phoneNumber, isDirectCharge, selectedOperator);
    }

    public void getPosInfo()
    {
        String pan ="";
        String pass = "";
        String posCode = "";
        try {
            File file = new File("data.txt");
            Scanner filescanner = new Scanner(file);
            pan = filescanner.nextLine();
            pass = filescanner.nextLine();
            filescanner.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Error: فایل اطلاعات دستگاه پیدا نشد");
            System.out.println("\nEnter to try again ");
            scanner.nextLine();
            MainMenu mainMenu = new MainMenu();
            mainMenu.start();
        }
        //If Validation Password Service
        posinfo = new Posinfo(pan, LocalDate.now(), posCode);
        paymentSms = new PaymentSms(pan);
        System.out.print("\nReceiving Card Information From File...... \n");
    }
}
