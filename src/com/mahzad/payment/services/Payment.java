package com.mahzad.payment.services;

import java.time.LocalDate;
import java.util.Scanner;

import com.mahzad.payment.contract.Fee;
import com.mahzad.payment.enums.Menu;

public abstract class Payment implements Fee {
    private Scanner scanner = new Scanner(System.in);
    protected final PaymentSms paymentSms;
    protected double fee;
    protected String cardNumber;
    protected LocalDate withdrawalDate;
    protected final String bankAccountNumber;
    protected final Menu selectedMenu;

    public Payment(PaymentSms paymentSms, Posinfo posinfo)
    {
        this.paymentSms = paymentSms;
        this.bankAccountNumber = posinfo.bankAccountNumber;
        this.cardNumber = posinfo.cardNumber;
        this.withdrawalDate = posinfo.withdrawalDate;
        this.selectedMenu = posinfo.selectedMenu;
    }

    @Override
    public void calculateFee() {
        switch(selectedMenu)
        {
            case AccountBalance:
                this.fee = 10;
                break;

            case Buy:
                this.fee = 20;
                break;

            case Bill:
                this.fee = 30;
                break;

            case Charg:
                this.fee = 40;
                break;

            default :
                this.fee = 0;

        };
    }

    public abstract void processPayment();

    public void backToMainMenu()
    {
        System.out.println("\nEnter to back main menu ");
        scanner.nextLine();
        MainMenu mainMenu = new MainMenu();
        mainMenu.start();
    }

    protected void  sendSMS(String message)
    {
        this.paymentSms.sendSMS(message);
    }

    protected  double getBalance()
    {
        //by cardNumber from a service
        return 2000;
    }

    protected void transfer(String destinationAccount, String  cardNumber, double amount)
    {
        //جابه جایی وجه
    }

    protected void printReceipt(double withdrawalAmount)
    {
        String hiddenCardNumber = cardNumber.substring(0,8)+"****"+cardNumber.substring(12,16);
        System.out.println("\nReceipt: Withdrawal " + withdrawalAmount + "Rial by cardNumber: " + hiddenCardNumber + "---------------------" + withdrawalDate );
    }

    protected  boolean payConfirmation(String confirmationMessage)
    {
        System.out.println(confirmationMessage +
                "\nThe fee is: "+ fee + "Rial"+
                "\nDo you want to continue? (press y to continue or anything else to cancel)");
        String billConfirmation = scanner.nextLine();
        return billConfirmation.equals("y");
    }
}
