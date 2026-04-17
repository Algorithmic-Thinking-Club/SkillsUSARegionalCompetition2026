package console;
import java.util.Scanner;

public class BMIConsole {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== BMI Calculator ===");
            try {
                System.out.print("Weight (lbs): ");
                double weight = Double.parseDouble(sc.next().trim());
                System.out.print("Height - feet: ");
                double feet = Double.parseDouble(sc.next().trim());
                System.out.print("Height - inches: ");
                double inches = Double.parseDouble(sc.next().trim());

                if (weight <= 0 || feet < 0 || inches < 0) {
                    System.out.println("Inputs must be positive."); continue;
                }

                double totalIn = feet * 12 + inches;
                double bmi = (weight * 703.0) / (totalIn * totalIn);
                String cat = bmi < 18.5 ? "Underweight"
                           : bmi < 25.0 ? "Normal"
                           : bmi < 30.0 ? "Overweight" : "Obese";

                System.out.printf("BMI: %.2f  |  Category: %s%n", bmi, cat);
            } catch (Exception e) { System.out.println("Invalid input, try again."); }

            System.out.print("Calculate again? (y/n): ");
            if (!sc.next().equalsIgnoreCase("y")) break;
        }
        System.out.println("Goodbye!");
    }
}