package console;
import java.util.Scanner;

public class BankConsole {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double balance = 0;

        while (true) {
            // show menu with current balance
            System.out.printf("%nBank Account | balance: $%.2f%n", balance);
            System.out.println("1. Deposit\n2. Withdraw\n3. Check Balance\n4. Exit");
            System.out.print("pick: ");

            int choice;
            try { choice = Integer.parseInt(sc.next().trim()); }
            catch (Exception e) { System.out.println("pick 1-4"); continue; } // safety

            if (choice == 4) break;
            if (choice == 3) { System.out.printf("balance: $%.2f%n", balance); continue; }
            if (choice != 1 && choice != 2) { System.out.println("pick 1-4"); continue; }

            System.out.print("amount: $");
            try {
                double amt = Double.parseDouble(sc.next().trim());
                if (amt <= 0) { System.out.println("has to be positive"); continue; }
                if (choice == 1) {
                    balance += amt;
                    System.out.printf("deposited $%.2f, new balance: $%.2f%n", amt, balance);
                } else {
                    if (amt > balance) { System.out.println("not enough money"); continue; } // cant overdraft
                    balance -= amt;
                    System.out.printf("withdrew $%.2f, new balance: $%.2f%n", amt, balance);
                }
            } catch (Exception e) { System.out.println("thats not a number"); }
        }
        System.out.println("bye!");
    }
}
