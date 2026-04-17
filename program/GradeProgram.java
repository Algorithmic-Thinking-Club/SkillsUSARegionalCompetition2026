
import javax.swing.*;
import java.awt.*;
import java.util.*; // added this new one for like hashmaps and array lists

public class GradeProgram {
    private Map<String, java.util.List<Double>> gradesByClass = new HashMap<>(); // creating a hashmap to store table of values

    public static void main(String[] args) { // main function, creating the grade program
        new GradeProgram().createGUI();
    }

    private void createGUI() {
        JFrame frame = new JFrame("Ashwath Polali, Contestant 4 - Program 2 - Grade Program!"); // just creating the frame
        frame.setLayout(new BorderLayout(10, 10));

        gradesByClass.put("Programming", new ArrayList<>()); // creating arraylists for all the options on the paper
        gradesByClass.put("Art", new ArrayList<>());
        gradesByClass.put("Science", new ArrayList<>());
        gradesByClass.put("Math", new ArrayList<>());
        gradesByClass.put("History", new ArrayList<>());

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10)); // input panel
        
        JComboBox<String> classSelector = new JComboBox<>(new String[] { // creating the class selector combo box to switch between classes
            "Programming", "Art", "Science", "Math", "History"
        });
        JTextField gradeInput = new JTextField(); // will be used more, but initializing the input grae field

        inputPanel.add(new JLabel("Select Class:")); // adding the two option boxes
        inputPanel.add(classSelector);
        inputPanel.add(new JLabel("Enter Grade:"));
        inputPanel.add(gradeInput);

        JTextArea outputArea = new JTextArea(10, 40); // the entire output area where outputs will go
        outputArea.setEditable(false); // safety
        JScrollPane scrollPane = new JScrollPane(outputArea); // for scrolling between the output area

        JPanel buttonPanel = new JPanel(); // button initlizaton
        JButton calcButton = new JButton("Calculate");
        JButton clearButton = new JButton("Clear");
        JButton exitButton = new JButton("Exit");

        calcButton.addActionListener(e -> { // this is the actual function for the calc button
            try {
                String selectedClass = (String) classSelector.getSelectedItem(); // basically get a class
                double grade = parseGrade(gradeInput); // parse the grade

                gradesByClass.get(selectedClass).add(grade); // basically for each selected class add the grade
                updateDisplay(selectedClass, outputArea); // update the display
            } catch (Exception ex) {
                outputArea.setText("Error " + ex.getMessage()); // exception, if the inputs just say that error
            }
        });

        clearButton.addActionListener(e -> { // for the clear button, just clear the output area nd grade input, not th ecalss selector
            gradeInput.setText("");
            outputArea.setText("");
        });

        exitButton.addActionListener(e -> { // exit
            System.exit(0);
        });

        buttonPanel.add(calcButton); // adding buttons to frame
        buttonPanel.add(clearButton);
        buttonPanel.add(exitButton);

        frame.add(inputPanel, BorderLayout.NORTH); // pieceing together the 3 panels
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void updateDisplay(String className, JTextArea outputArea) { // this is where outputArea gets updated
        java.util.List<Double> grades = gradesByClass.get(className); // list of the grades
        if(grades.isEmpty()) {
            outputArea.setText("No grades entered for " + className); // if they are empty throw this error
            return;
        }
        double sum = 0; // for adding the sum of grades
        for (double g: grades) {
            sum+=g;
        }

        double rawAvg = sum / grades.size(); // averages, and creating the rounding as mentioned in the packet to ensure everything is whole numbered
        long average = Math.round(rawAvg);
        
        double high = Collections.max(grades); // high and low of the grade
        double low = Collections.min(grades);

        String output = "Class Name: " + className + "\n" + // creating the output string that goes in the oputputArea, with class name (other things mentioned in packet)
                        "Scores Entered: " + grades + "\n" +
                       "Current Average: " + average + "\n" +
                       "High Score: " + high + "\n" +
                       "Low Score: " + low;
        outputArea.setText(output); // this is where that string gets put in the output Area
    }

    private double parseGrade(JTextField field) {  // parsing the grade for safety and returning the grade
        try {
            String text = field.getText().trim(); // get the text from the field
            if (text.isEmpty()) throw new Exception("Grade cannot be empty"); // ensure its not empty
            double grade = Double.parseDouble(text);
            if (grade < 0 || grade > 100) throw new Exception("Grade must be 0-100"); // confining value
            return grade; // return the grade
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage()); // throw exception if runtime error / anything else
        }
    } 
}
