import Services.*;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
     try {
         MainMenu mainMenu = new MainMenu();
         mainMenu.start();
     }
     catch (Exception e) {
         System.out.println("Error: " + e.getMessage());
         System.out.println("Press any key to continue...");
         Scanner scanner = new Scanner(System.in);
         scanner.nextLine();
         main(args);
     }
    }

}