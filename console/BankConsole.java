package console;
import java.util.Scanner;

public class BankConsole {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double balance = 0;

        while (true) {
            System.out.printf("%n=== Bank Account (Balance: $%.2f) ===%n", balance);
            System.out.println("1. Deposit\n2. Withdraw\n3. Check Balance\n4. Exit");
            System.out.print("Choice: ");

            int choice;
            try { choice = Integer.parseInt(sc.next().trim()); }
            catch (Exception e) { System.out.println("Enter 1-4."); continue; }

            if (choice == 4) break;
            if (choice == 3) { System.out.printf("Balance: $%.2f%n", balance); continue; }
            if (choice != 1 && choice != 2) { System.out.println("Enter 1-4."); continue; }

            System.out.print("Amount: $");
            try {
                double amt = Double.parseDouble(sc.next().trim());
                if (amt <= 0) { System.out.println("Must be positive."); continue; }
                if (choice == 1) {
                    balance += amt;
                    System.out.printf("Deposited $%.2f. New balance: $%.2f%n", amt, balance);
                } else {
                    if (amt > balance) { System.out.println("Insufficient funds."); continue; }
                    balance -= amt;
                    System.out.printf("Withdrew $%.2f. New balance: $%.2f%n", amt, balance);
                }
            } catch (Exception e) { System.out.println("Invalid amount."); }
        }
        System.out.println("Goodbye!");
    }
}