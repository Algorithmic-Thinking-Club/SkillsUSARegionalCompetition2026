package console;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class InventoryConsole {
    static List<String[]> items = new ArrayList<>(); // each item stored as {name, qty, price}
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            // menu
            System.out.println("\nInventory\n1. Add Item\n2. Show All\n3. Exit");
            System.out.print("pick: ");
            switch (sc.next().trim()) {
                case "1": addItem(); break;
                case "2": showAll(); break;
                case "3": System.out.println("bye!"); return;
                default:  System.out.println("pick 1-3");
            }
        }
    }

    static void addItem() {
        try {
            System.out.print("item name: "); String name = sc.next().trim();
            System.out.print("quantity: ");  int qty = Integer.parseInt(sc.next().trim());
            System.out.print("price: $");    double price = Double.parseDouble(sc.next().trim());
            if (qty <= 0 || price <= 0) { System.out.println("qty and price gotta be positive"); return; }
            items.add(new String[]{name, String.valueOf(qty), String.valueOf(price)});
            System.out.println("added: " + name);
        } catch (Exception e) { System.out.println("bad input"); } // safety
    }

    static void showAll() {
        if (items.isEmpty()) { System.out.println("nothing in here yet"); return; }
        double total = 0, maxP = 0;
        String maxName = "";
        System.out.println("\ninventory:");
        for (String[] it : items) {
            int q = Integer.parseInt(it[1]);
            double p = Double.parseDouble(it[2]);
            double v = q * p; // value of this item
            total += v;
            if (p > maxP) { maxP = p; maxName = it[0]; }
            System.out.printf("%-15s Qty:%-4d $%-8.2f Value:$%.2f%n", it[0], q, p, v);
        }
        System.out.printf("total value: $%.2f  |  most expensive: %s ($%.2f)%n", total, maxName, maxP);
    }
}
