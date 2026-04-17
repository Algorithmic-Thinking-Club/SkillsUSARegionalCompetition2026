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
        for (String c : classes) grades.put(c, new ArrayList<>()); // init each class with empty list

        while (true) {
            // show the class menu
            System.out.println("\nGrade Tracker");
            for (int i = 0; i < classes.length; i++)
                System.out.println((i + 1) + ". " + classes[i]);
            System.out.println("6. Exit");
            System.out.print("pick a class: ");

            int choice;
            try { choice = Integer.parseInt(sc.next().trim()); }
            catch (Exception e) { System.out.println("thats not valid"); continue; } // safety

            if (choice == 6) break;
            if (choice < 1 || choice > 5) { System.out.println("pick 1-6"); continue; }

            String cls = classes[choice - 1];
            System.out.print("enter grade (0-100) or -1 to see stats: ");
            try {
                double g = Double.parseDouble(sc.next().trim());
                if (g == -1) {
                    printStats(cls, grades.get(cls));
                } else if (g >= 0 && g <= 100) {
                    grades.get(cls).add(g);
                    System.out.println("added " + g + " to " + cls);
                    printStats(cls, grades.get(cls));
                } else {
                    System.out.println("grade has to be 0-100");
                }
            } catch (Exception e) { System.out.println("bad input"); }
        }
        System.out.println("bye!");
        sc.close();
    }

    static void printStats(String name, List<Double> g) {
        if (g.isEmpty()) { System.out.println("no grades yet"); return; }
        double sum = 0;
        for (double v : g) sum += v;
        System.out.println("class: " + name + " | scores: " + g);
        System.out.println("avg: " + Math.round(sum / g.size())
            + " | high: " + Collections.max(g)
            + " | low: "  + Collections.min(g));
    }
}
