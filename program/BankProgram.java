import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class BankProgram {
    private double balance = 0.0;
    private StringBuilder history = new StringBuilder(); // keeping track of all transactions

    public static void main(String[] args) {
        new BankProgram().createGUI();
    }

    private void createGUI() {
        JFrame frame = new JFrame("[#] - Program 3 - Bank Account"); // just creating the frame
        frame.setLayout(new BorderLayout(10, 10));

        JTextField amtField = new JTextField();
        JLabel balLabel = new JLabel("balance: $0.00");
        balLabel.setFont(balLabel.getFont().deriveFont(16f)); // make balance label bigger
        JTextArea log = new JTextArea(10, 30);
        log.setEditable(false); // safety

        JPanel top = new JPanel(new GridLayout(2, 2, 5, 5));
        top.add(new JLabel("amount:")); top.add(amtField);
        top.add(new JLabel("current balance:")); top.add(balLabel);

        JButton deposit  = new JButton("Deposit");
        JButton withdraw = new JButton("Withdraw");
        JButton clearBtn = new JButton("Clear History");
        JButton exit     = new JButton("Exit");

        deposit.addActionListener(e -> {
            double amt = parseAmt(amtField, log);
            if (amt <= 0) return;
            balance += amt;
            history.append(String.format("deposit  +$%.2f  |  balance: $%.2f\n", amt, balance));
            refresh(log, balLabel);
        });

        withdraw.addActionListener(e -> {
            double amt = parseAmt(amtField, log);
            if (amt <= 0) return;
            if (amt > balance) { log.append("not enough money\n"); return; } // cant go negative
            balance -= amt;
            history.append(String.format("withdraw -$%.2f  |  balance: $%.2f\n", amt, balance));
            refresh(log, balLabel);
        });

        clearBtn.addActionListener(e -> {
            history.setLength(0);
            log.setText("");
        });

        exit.addActionListener(e -> System.exit(0));

        JPanel btns = new JPanel();
        btns.add(deposit); btns.add(withdraw); btns.add(clearBtn); btns.add(exit);

        // pieceing together the panels
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
            if (v <= 0) { log.append("has to be positive\n"); return -1; }
            return v;
        } catch (Exception e) { log.append("bad input\n"); return -1; } // safety
    }

    private void refresh(JTextArea log, JLabel lbl) {
        log.setText(history.toString());
        lbl.setText(String.format("balance: $%.2f", balance));
    }
}
