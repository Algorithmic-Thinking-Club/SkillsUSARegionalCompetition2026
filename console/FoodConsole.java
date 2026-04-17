package console;
import java.util.Scanner;

public class FoodConsole {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double runningTotal = 0;

        while (true) {
            System.out.println("\n=== Food Truck Order ===");
            double total = 0;
            total += askQty(sc, "Hot Dog ($2.50)") * 2.50;
            total += askQty(sc, "Brat ($3.50)")    * 3.50;
            total += askQty(sc, "Hamburger ($5.00)") * 5.00;
            total += askQty(sc, "Fries ($2.00)")   * 2.00;
            total += askQty(sc, "Soda ($2.00)")    * 2.00;
            askQty(sc, "Water (Free)"); // free, no cost added
            runningTotal += total;

            System.out.printf("Order Total:   $%.2f%n", total);
            System.out.printf("Running Total: $%.2f%n", runningTotal);
            System.out.print("Another order? (y/n): ");
            if (!sc.next().equalsIgnoreCase("y")) break;
        }
        System.out.println("Goodbye!");
    }

    private static double askQty(Scanner sc, String item) {
        System.out.print("Qty for " + item + ": ");
        try {
            double v = Double.parseDouble(sc.next().trim());
            return v < 0 ? 0 : v;
        } catch (Exception e) { return 0; }
    }
}