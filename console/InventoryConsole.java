package console;
import java.util.*;

public class InventoryConsole {
    static List<String[]> items = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Inventory ===\n1. Add Item\n2. Show All\n3. Exit");
            System.out.print("Choice: ");
            switch (sc.next().trim()) {
                case "1": addItem(); break;
                case "2": showAll(); break;
                case "3": System.out.println("Goodbye!"); return;
                default:  System.out.println("Enter 1-3.");
            }
        }
    }

    static void addItem() {
        try {
            System.out.print("Item name: "); String name = sc.next().trim();
            System.out.print("Quantity: ");  int qty = Integer.parseInt(sc.next().trim());
            System.out.print("Price: $");    double price = Double.parseDouble(sc.next().trim());
            if (qty <= 0 || price <= 0) { System.out.println("Qty/price must be positive."); return; }
            items.add(new String[]{name, String.valueOf(qty), String.valueOf(price)});
            System.out.println("Added: " + name);
        } catch (Exception e) { System.out.println("Invalid input."); }
    }

    static void showAll() {
        if (items.isEmpty()) { System.out.println("No items."); return; }
        double total = 0, maxP = 0;
        String maxName = "";
        System.out.println("\n--- INVENTORY ---");
        for (String[] it : items) {
            int q = Integer.parseInt(it[1]);
            double p = Double.parseDouble(it[2]);
            double v = q * p;
            total += v;
            if (p > maxP) { maxP = p; maxName = it[0]; }
            System.out.printf("%-15s Qty:%-4d $%-8.2f Value:$%.2f%n", it[0], q, p, v);
        }
        System.out.printf("Total Value: $%.2f  |  Most Expensive: %s ($%.2f)%n", total, maxName, maxP);
    }
}