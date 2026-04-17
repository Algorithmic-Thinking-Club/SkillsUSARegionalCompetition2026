
import javax.swing.*;
import java.awt.*;

public class BankProgram {
    private double balance = 0.0;
    private StringBuilder history = new StringBuilder();

    public static void main(String[] args) {
        new BankProgram().createGUI();
    }

    private void createGUI() {
        JFrame frame = new JFrame("Ash - Program 3 - Bank Account");
        frame.setLayout(new BorderLayout(10, 10));

        JTextField amtField = new JTextField();
        JLabel balLabel = new JLabel("Balance: $0.00");
        balLabel.setFont(balLabel.getFont().deriveFont(16f));
        JTextArea log = new JTextArea(10, 30);
        log.setEditable(false);

        JPanel top = new JPanel(new GridLayout(2, 2, 5, 5));
        top.add(new JLabel("Amount:")); top.add(amtField);
        top.add(new JLabel("Current Balance:")); top.add(balLabel);

        JButton deposit  = new JButton("Deposit");
        JButton withdraw = new JButton("Withdraw");
        JButton clearBtn = new JButton("Clear History");
        JButton exit     = new JButton("Exit");

        deposit.addActionListener(e -> {
            double amt = parseAmt(amtField, log);
            if (amt <= 0) return;
            balance += amt;
            history.append(String.format("DEPOSIT  +$%.2f  |  Balance: $%.2f\n", amt, balance));
            refresh(log, balLabel);
        });

        withdraw.addActionListener(e -> {
            double amt = parseAmt(amtField, log);
            if (amt <= 0) return;
            if (amt > balance) { log.append("Insufficient funds.\n"); return; }
            balance -= amt;
            history.append(String.format("WITHDRAW -$%.2f  |  Balance: $%.2f\n", amt, balance));
            refresh(log, balLabel);
        });

        clearBtn.addActionListener(e -> {
            history.setLength(0);
            log.setText("");
        });

        exit.addActionListener(e -> System.exit(0));

        JPanel btns = new JPanel();
        btns.add(deposit); btns.add(withdraw); btns.add(clearBtn); btns.add(exit);

        frame.add(top, BorderLayout.NORTH);
        frame.add(new JScrollPane(log), BorderLayout.CENTER);
        frame.add(btns, BorderLayout.SOUTH);
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private double parseAmt(JTextField f, JTextArea log) {
        try {
            double v = Double.parseDouble(f.getText().trim());
            if (v <= 0) { log.append("Amount must be positive.\n"); return -1; }
            return v;
        } catch (Exception e) { log.append("Invalid input.\n"); return -1; }
    }

    private void refresh(JTextArea log, JLabel lbl) {
        log.setText(history.toString());
        lbl.setText(String.format("Balance: $%.2f", balance));
    }
}