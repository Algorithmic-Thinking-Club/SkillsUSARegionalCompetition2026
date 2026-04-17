import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class InventoryProgram {
    private List<String[]> items = new ArrayList<>(); // each entry: {name, qty, price}

    public static void main(String[] args) {
        new InventoryProgram().createGUI();
    }

    private void createGUI() {
        JFrame frame = new JFrame("Your Name - Program 4 - Inventory");
        frame.setLayout(new BorderLayout(10, 10));

        JTextField nameF  = new JTextField();
        JTextField qtyF   = new JTextField();
        JTextField priceF = new JTextField();
        JTextArea output  = new JTextArea(12, 40);
        output.setEditable(false);

        JPanel form = new JPanel(new GridLayout(3, 2, 5, 5));
        form.add(new JLabel("Item Name:"));     form.add(nameF);
        form.add(new JLabel("Quantity:"));       form.add(qtyF);
        form.add(new JLabel("Price Per Unit:")); form.add(priceF);

        JButton add   = new JButton("Add Item");
        JButton show  = new JButton("Show Inventory");
        JButton clear = new JButton("Clear");
        JButton exit  = new JButton("Exit");

        add.addActionListener(e -> {
            try {
                String name = nameF.getText().trim();
                if (name.isEmpty()) { output.setText("Item name required."); return; }
                int qty = Integer.parseInt(qtyF.getText().trim());
                double price = Double.parseDouble(priceF.getText().trim());
                if (qty <= 0 || price <= 0) { output.setText("Qty and price must be positive."); return; }
                items.add(new String[]{name, String.valueOf(qty), String.valueOf(price)});
                output.setText("Added: " + name + " (Qty: " + qty + ", $" + price + ")");
                nameF.setText(""); qtyF.setText(""); priceF.setText("");
            } catch (Exception ex) { output.setText("Invalid input: " + ex.getMessage()); }
        });

        show.addActionListener(e -> {
            if (items.isEmpty()) { output.setText("No items in inventory."); return; }
            StringBuilder sb = new StringBuilder("=== INVENTORY ===\n");
            double totalVal = 0, maxPrice = 0;
            String maxItem = "";
            for (String[] item : items) {
                int qty = Integer.parseInt(item[1]);
                double price = Double.parseDouble(item[2]);
                double val = qty * price;
                totalVal += val;
                if (price > maxPrice) { maxPrice = price; maxItem = item[0]; }
                sb.append(String.format("%-15s Qty:%-5d $%-8.2f Value:$%.2f\n", item[0], qty, price, val));
            }
            sb.append(String.format("\nTotal Value: $%.2f\nMost Expensive: %s ($%.2f)", totalVal, maxItem, maxPrice));
            output.setText(sb.toString());
        });

        clear.addActionListener(e -> { items.clear(); output.setText("Inventory cleared."); });
        exit.addActionListener(e -> System.exit(0));

        JPanel btns = new JPanel();
        btns.add(add); btns.add(show); btns.add(clear); btns.add(exit);

        frame.add(form, BorderLayout.NORTH);
        frame.add(new JScrollPane(output), BorderLayout.CENTER);
        frame.add(btns, BorderLayout.SOUTH);
        frame.setSize(550, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}