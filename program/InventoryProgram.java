import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;
import java.util.ArrayList;

public class InventoryProgram {
    private List<String[]> items = new ArrayList<>(); // each entry: {name, qty, price}

    public static void main(String[] args) {
        new InventoryProgram().createGUI();
    }

    private void createGUI() {
        JFrame frame = new JFrame("[#] - Program 4 - Inventory"); // just creating the frame
        frame.setLayout(new BorderLayout(10, 10));

        // input fields
        JTextField nameF  = new JTextField();
        JTextField qtyF   = new JTextField();
        JTextField priceF = new JTextField();
        JTextArea output  = new JTextArea(12, 40);
        output.setEditable(false); // safety

        JPanel form = new JPanel(new GridLayout(3, 2, 5, 5));
        form.add(new JLabel("item name:"));     form.add(nameF);
        form.add(new JLabel("quantity:"));       form.add(qtyF);
        form.add(new JLabel("price per unit:")); form.add(priceF);

        JButton add   = new JButton("Add Item");
        JButton show  = new JButton("Show Inventory");
        JButton clear = new JButton("Clear");
        JButton exit  = new JButton("Exit");

        add.addActionListener(e -> {
            try {
                String name = nameF.getText().trim();
                if (name.isEmpty()) { output.setText("need an item name"); return; }
                int qty = Integer.parseInt(qtyF.getText().trim());
                double price = Double.parseDouble(priceF.getText().trim());
                if (qty <= 0 || price <= 0) { output.setText("qty and price gotta be positive"); return; }
                items.add(new String[]{name, String.valueOf(qty), String.valueOf(price)});
                output.setText("added: " + name + " (qty: " + qty + ", $" + price + ")");
                nameF.setText(""); qtyF.setText(""); priceF.setText(""); // clear fields after adding
            } catch (Exception ex) { output.setText("bad input: " + ex.getMessage()); }
        });

        show.addActionListener(e -> {
            if (items.isEmpty()) { output.setText("nothing in inventory yet"); return; }
            StringBuilder sb = new StringBuilder("inventory\n");
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
            sb.append(String.format("\ntotal value: $%.2f\nmost expensive: %s ($%.2f)", totalVal, maxItem, maxPrice));
            output.setText(sb.toString());
        });

        clear.addActionListener(e -> { items.clear(); output.setText("cleared"); });
        exit.addActionListener(e -> System.exit(0));

        JPanel btns = new JPanel();
        btns.add(add); btns.add(show); btns.add(clear); btns.add(exit);

        // putting the panels together
        frame.add(form, BorderLayout.NORTH);
        frame.add(new JScrollPane(output), BorderLayout.CENTER);
        frame.add(btns, BorderLayout.SOUTH);
        frame.setSize(550, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
