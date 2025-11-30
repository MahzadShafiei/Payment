import Enums.Menu;
import Services.*;

import java.time.LocalDate;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        for (Menu menu : Menu.values()) {
            System.out.println(menu.getMenuCode() + ". " + menu.getMenuName());
        }
        System.out.println("Please return you desired menu code: ");
        Scanner scanner = new Scanner(System.in);
        int selectedMenuCode = scanner.nextInt();

        Menu selectedMenu = Menu.fromCode(selectedMenuCode);
        System.out.println("\n************    " + selectedMenu.getMenuName() + "    ************");

        Posinfo posinfo = getCardPosInfo();
        PaymentSms paymentSms = new PaymentSms();

        Payment payment;
        switch(selectedMenu)
        {
            case AccountBalance:
                getCardPassword();
                payment = new AccountBalance(posinfo, paymentSms, posinfo.cardNumber, posinfo.withdrawalDate, 0, selectedMenu);
            break;

            case Buy:
                System.out.println("Please the price: ");
                Scanner priceScanner = new Scanner(System.in);
                double price = priceScanner.nextDouble();
                getCardPassword();
                payment = new Buy(posinfo, paymentSms, posinfo.cardNumber, posinfo.withdrawalDate, price, selectedMenu);
                break;
            default :
                    payment = null;

        };

        payment.processPayment();
    }

    public static Posinfo getCardPosInfo()
    {
        Posinfo posinfo = new Posinfo("5859", LocalDate.now(), 2);
        return posinfo;
    }

    public static void getCardPassword()
    {
        System.out.println("Please enter your card password: ");
        Scanner scanner = new Scanner(System.in);
        String cardPassword = scanner.nextLine();
        //Validation
    }

}