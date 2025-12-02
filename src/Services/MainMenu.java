package Services;

import Enums.Menu;
import java.time.LocalDate;
import java.util.Scanner;

public class MainMenu {

    Scanner scanner = new Scanner(System.in);
    Posinfo posinfo = getCardPosInfo();
    PaymentSms paymentSms = new PaymentSms();
    Payment payment;

    public void start()
    {
        for (Menu menu : Menu.values())
            System.out.println(menu.getMenuCode() + ". " + menu.getMenuName());

        System.out.println("Please return you desired menu code: ");
        int selectedMenuCode = Integer.parseInt(scanner.nextLine());

        Menu selectedMenu = Menu.fromCode(selectedMenuCode);
        System.out.println("\n************    " + selectedMenu.getMenuName() + "    ************");

        switch(selectedMenu)
        {
            case AccountBalance:
                accountBalanceProccess(selectedMenu);
                break;

            case Buy:
                buyProccess(selectedMenu);
                break;

            case Bill:
                billProccess(selectedMenu);
                break;

            case Charg:
                chargeProccess(selectedMenu);
                break;

            default :
                payment = null;

        };

        payment.processPayment();
    }

    private void accountBalanceProccess(Menu selectedMenu)
    {
        getCardPassword();
        payment = new AccountBalance(posinfo, paymentSms, posinfo.cardNumber, posinfo.withdrawalDate, selectedMenu);
    }

    private void buyProccess(Menu selectedMenu)
    {
        System.out.println("Please enter the price: ");
        double price = Double.parseDouble(scanner.nextLine());
        getCardPassword();
        payment = new Buy(posinfo, paymentSms, posinfo.cardNumber, posinfo.withdrawalDate, price, selectedMenu);
    }

    private void billProccess(Menu selectedMenu)
    {
        System.out.println("Please enter the bill Id: ");
        double billId = Double.parseDouble(scanner.nextLine());
        System.out.println("Please enter the Payment Id: ");
        double paymentId = Double.parseDouble(scanner.nextLine());
        getCardPassword();
        payment = new Bill(posinfo, paymentSms, posinfo.cardNumber, posinfo.withdrawalDate, selectedMenu, billId, paymentId);
    }

    private void chargeProccess(Menu selectedMenu)
    {
        System.out.println("1. Direct");
        System.out.println("2. Pin");
        int selectedChargeMenu = Integer.parseInt(scanner.nextLine());
        System.out.println("Please enter the amount: ");
        double amount = Double.parseDouble(scanner.nextLine());
        System.out.println("Please enter the phone number: ");
        String phoneNumber = scanner.nextLine();
        getCardPassword();
        boolean isDirectCharge = selectedChargeMenu ==1;
        payment = new ChargSimHamrah(posinfo, paymentSms, posinfo.cardNumber, posinfo.withdrawalDate,amount, selectedMenu, phoneNumber, isDirectCharge);
    }

    public Posinfo getCardPosInfo()
    {
        return new Posinfo("5859", LocalDate.now(), 2);
    }

    public void getCardPassword()
    {
        //System.out.println("Please enter your card password: ");
        //String cardPassword = scanner.nextLine();
        String cardPassword;
        do {
            System.out.print("Please enter your 4-digit password: ");
            cardPassword = scanner.nextLine().trim();
        }
        while (!cardPassword.matches("\\d{4}"));
        //Validation Password
    }
}
