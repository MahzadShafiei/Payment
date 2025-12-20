package com.mahzad.payment.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

import com.mahzad.payment.contract.Fee;
import com.mahzad.payment.enums.Menu;
import common.ConfigLoadException;

public abstract class Payment implements Fee {
    private final Scanner scanner = new Scanner(System.in);
    protected final PaymentSms paymentSms;
    protected double fee;
    protected String cardNumber;
    protected LocalDateTime withdrawalDate;
    protected final String bankAccountNumber;
    protected final Menu selectedMenu;
    protected boolean overallResult = true;

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

    protected void followingProcess(double withdrawalAmount)
    {
        try {
            getPassInfo();
            logReport();
            printReceipt(withdrawalAmount);
            backToMainMenu();

        }
        catch (IOException e) {
            throw  new ConfigLoadException("Failed to log report", e);
        }
        catch (Exception e) {
            throw  new ConfigLoadException(e.getMessage(), e);
        }
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

    private void logReport () throws IOException
    {
        String hiddenCardNumber = cardNumber.substring(0,6)+"****"+cardNumber.substring(12,16);
        //دریافت اطلاعات بانک مبدا از طریق سرویس
        Path path = Paths.get("report.txt");
        String report = "Result:"+overallResult+"Bank:MiddleEastBank,Pan:"+hiddenCardNumber+",Date:"+withdrawalDate.toString()+"\n";
        Files.write(path, report.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    private void printReceipt(double withdrawalAmount)
    {
        String hiddenCardNumber = cardNumber.substring(0,8)+"****"+cardNumber.substring(12,16);
        if(overallResult)
            System.out.println("\nReceipt: Withdrawal " + withdrawalAmount + " Rial by cardNumber: " + hiddenCardNumber + "---------------------" + withdrawalDate );
        else
            System.out.println("\nError: The process by cardNumber: " + hiddenCardNumber + " was unsuccessful---------------------" + withdrawalDate );
    }

    private void backToMainMenu()
    {
        System.out.println("\nEnter to back main menu ");
        scanner.nextLine();
        MainMenu mainMenu = new MainMenu();
        mainMenu.start();
    }

    protected  boolean payConfirmation(String confirmationMessage)
    {
        System.out.println(confirmationMessage +
                "\nThe fee is: "+ fee + "Rial"+
                "\nDo you want to continue? (press y to continue or anything else to cancel)");
        String billConfirmation = scanner.nextLine();
        return billConfirmation.equals("y");
    }

    private void getPassInfo()
    {
        int counter = 0;
        String pass = "";

        do {
            if(counter>2)
                throw new IllegalArgumentException("Card password is not correct, try again another time.");
            counter++;
            System.out.println("Please enter your card password: ");
            pass = scanner.nextLine();
        }
        while (pass.length()!=4);

    }
}
