import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.GridLayout;

public class BMIProgram {
    public static void main(String[] args) {
        new BMIProgram().createGUI();
    }

    private void createGUI() {
        JFrame frame = new JFrame("[#] - Program 5 - BMI Calculator"); // just creating the frame
        frame.setLayout(new GridLayout(7, 2, 10, 10));

        // input fields
        JTextField weightF = new JTextField();
        JTextField feetF   = new JTextField();
        JTextField inchF   = new JTextField();
        JLabel bmiLbl = new JLabel("BMI: --");
        JLabel catLbl = new JLabel("Category: --");

        frame.add(new JLabel("weight (lbs):")); frame.add(weightF);
        frame.add(new JLabel("height - feet:")); frame.add(feetF);
        frame.add(new JLabel("height - inches:")); frame.add(inchF);
        frame.add(bmiLbl); frame.add(catLbl);

        JButton calc  = new JButton("Calculate");
        JButton clear = new JButton("Clear");
        JButton exit  = new JButton("Exit");

        calc.addActionListener(e -> {
            try {
                double weight = Double.parseDouble(weightF.getText().trim());
                double feet   = Double.parseDouble(feetF.getText().trim());
                double inches = Double.parseDouble(inchF.getText().trim());
                if (weight <= 0 || feet < 0 || inches < 0) {
                    bmiLbl.setText("BMI: values gotta be positive"); return;
                }
                double totalIn = feet * 12 + inches; // convert to total inches
                double bmi = (weight * 703.0) / (totalIn * totalIn); // standard formula
                String cat = bmi < 18.5 ? "Underweight"
                           : bmi < 25.0 ? "Normal"
                           : bmi < 30.0 ? "Overweight" : "Obese";
                bmiLbl.setText(String.format("BMI: %.2f", bmi));
                catLbl.setText("Category: " + cat);
            } catch (Exception ex) { bmiLbl.setText("BMI: check your numbers"); } // safety
        });

        clear.addActionListener(e -> {
            weightF.setText(""); feetF.setText(""); inchF.setText("");
            bmiLbl.setText("BMI: --"); catLbl.setText("Category: --");
        });

        exit.addActionListener(e -> System.exit(0));

        frame.add(calc); frame.add(clear); frame.add(exit);
        frame.setSize(420, 320);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
