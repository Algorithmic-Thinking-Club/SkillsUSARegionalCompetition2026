package console;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.Scanner;

public class GradeConsole {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] classes = {"Programming", "Art", "Science", "Math", "History"};
        Map<String, List<Double>> grades = new HashMap<>();
        for (String c : classes) grades.put(c, new ArrayList<>());

        while (true) {
            System.out.println("\n=== Grade Tracker ===");
            for (int i = 0; i < classes.length; i++)
                System.out.println((i + 1) + ". " + classes[i]);
            System.out.println("6. Exit");
            System.out.print("Choose class: ");

            int choice;
            try { choice = Integer.parseInt(sc.next().trim()); }
            catch (Exception e) { System.out.println("Invalid."); continue; }

            if (choice == 6) break;
            if (choice < 1 || choice > 5) { System.out.println("Choose 1-6."); continue; }

            String cls = classes[choice - 1];
            System.out.print("Enter grade (0-100) or -1 to view stats: ");
            try {
                double g = Double.parseDouble(sc.next().trim());
                if (g == -1) {
                    printStats(cls, grades.get(cls));
                } else if (g >= 0 && g <= 100) {
                    grades.get(cls).add(g);
                    System.out.println("Added " + g + " to " + cls);
                    printStats(cls, grades.get(cls));
                } else {
                    System.out.println("Grade must be 0-100.");
                }
            } catch (Exception e) { System.out.println("Invalid input."); }
        }
        System.out.println("Goodbye!");
        sc.close();
    }

    static void printStats(String name, List<Double> g) {
        if (g.isEmpty()) { System.out.println("No grades yet."); return; }
        double sum = 0;
        for (double v : g) sum += v;
        System.out.println("Class: " + name + " | Scores: " + g);
        System.out.println("Avg: " + Math.round(sum / g.size())
            + " | High: " + Collections.max(g)
            + " | Low: "  + Collections.min(g));
    }
}