package com.mahzad.payment;

import com.mahzad.payment.services.MainMenu;
import common.ConfigLoadException;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        retryStart(3);
    }

    private static void retryStart(int maxRetries) {
           for (int i = 0; i <= maxRetries; i++) {

            try {
                new MainMenu().start();
                return;
            }
            catch (ConfigLoadException e) {
                System.out.println("Error: " + e.getMessage());
            }

        }
        System.exit(1);
    }
}

