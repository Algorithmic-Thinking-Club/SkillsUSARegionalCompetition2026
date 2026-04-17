
import javax.swing.*; // imports for swing & awt
import java.awt.*;

public class FoodProgram {
    private double runningTotal = 0.0; // initializing the variable for the total (runningTotal)

    public FoodProgram(){
        
    }

    public static void main(String[] args) { // Main function where we create the FoodProgram.createGUI()
        new FoodProgram().createGUI();
    }

    private void createGUI() { // creatingGUI 
        JFrame frame = new JFrame("Ashwath Polali - Program 1 - Food program"); // frame title & layout
        frame.setLayout(new GridLayout(10, 2, 10, 10)); 

        // These are all the JTextField initializing for the items for sale
        JTextField hotDogQty = new JTextField();
        JTextField bratQty = new JTextField();
        JTextField hamburgerQty = new JTextField();
        JTextField friesQty = new JTextField();
        JTextField sodaQty = new JTextField();
        JTextField waterQty = new JTextField();

        JLabel orderTotalLabel = new JLabel("Order Total: $0.00"); // the labels for regular order total & running total
        JLabel runningTotalLabel = new JLabel("Running Total: $0.00");

        // creating all the JLabels for the items
        frame.add(new JLabel("Hot Dog ($2.50)")); 
        frame.add(hotDogQty);
        frame.add(new JLabel("Brat ($3.50)"));
        frame.add(bratQty);
        frame.add(new JLabel("Hamburger ($5.00)"));
        frame.add(hamburgerQty);
        frame.add(new JLabel("Soda ($2.00)"));
        frame.add(sodaQty);
        frame.add(new JLabel("Water (Free)"));
        frame.add(waterQty);

        frame.add(orderTotalLabel); // adding the total labels to the frame
        frame.add(runningTotalLabel);

        // Button initializing
        JButton calcButton = new JButton("Calculate");
        JButton clearButton = new JButton("Clear");
        JButton exitButton = new JButton("Exit");

        // Calculation button
        calcButton.addActionListener(e -> { // adding a action when clicked
            double total = parseQty(hotDogQty) * 2.50 + // this uses a parseQty (we will get to that later) for adding values for all scores
                            parseQty(bratQty) * 3.50 +
                            parseQty(hamburgerQty) * 5.00 +
                            parseQty(sodaQty) * 2.00;
            runningTotal += total; // This is where the regular total of the ORDER gets added to the runningTotal (all sales)
            orderTotalLabel.setText(String.format("Order Total: $%.2f", total)); // showing the updated text for the order and running total
            runningTotalLabel.setText(String.format("Running Total: $%.2f", runningTotal));
        });
        
        clearButton.addActionListener(e -> { // adding the action listener, for the clear button
            hotDogQty.setText("");
            bratQty.setText("");
            hamburgerQty.setText("");
            friesQty.setText("");
            sodaQty.setText("");
            waterQty.setText("");

            orderTotalLabel.setText("Order Total: $0.00"); // clearing order total
        });

        exitButton.addActionListener(e -> System.exit(0)); // just exiting from the GUI

        frame.add(calcButton); // adding the buttons to the frame
        frame.add(clearButton);
        frame.add(exitButton);

        frame.setSize(500, 400); // setting the size of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private double parseQty(JTextField field) { // parsing Qty, this basically ensures the safety of the text, so that letters dont get added / empty texts, essentially safety checking
        try {
            String text = field.getText().trim();
            if (text.isEmpty()) return 0.0; // if text empty return 0
            return Double.parseDouble(text); // java function for parsing the String into a Double
        } catch (Exception e) { // if exception just return 0 so nothing gets screwed up
            return 0.0;
        }
    }

}