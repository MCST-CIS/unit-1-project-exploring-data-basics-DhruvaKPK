/*
Name: Dhruva KPK
Project: Unit 1 Project: Exploring Data Basics
Date: 9/11/23
Extra: No extra needed
*/
import java.util.Scanner;

public class Main {
    // color codes for text output (Searched up how to do it more efficiently because I was tired of putting the seperate code individually twice a line)
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";
    public static final String YELLOW = "\u001B[33m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(YELLOW + "Welcome to GradeMaster!" + RESET);

        // get valid student count cannot be less than 1

      //I found out that I need to also put my scanner in as an arguement because it only works inside this class if I don't
        int studentCount = getValidInt(scanner, "Enter the number of students: ", 1);

        // arrays to store data for each student
        String[] studentNames = new String[studentCount];
        double[] studentAverages = new double[studentCount];
        boolean[] studentPassStatus = new boolean[studentCount];

        // loop for each student
        for (int i = 0; i < studentCount; i++) {
            System.out.println(YELLOW + "\nEntering data for Student " + (i + 1) + ":" + RESET);

            // get valid student name
            studentNames[i] = getValidName(scanner, "Enter student's name (letters only): ");

            // get valid subject count cannot be more than 12
            int subjectCount = getValidInt(scanner, "Enter the number of subjects (max 12): ", 1, 12);

            double totalWeightedScore = 0; // sum of weighted grades
            double totalWeight = 0; // sum of weights

            // loop for each subject
            for (int j = 0; j < subjectCount; j++) {
                System.out.println(YELLOW + "\nSubject " + (j + 1) + ":" + RESET);

                // get grade from 0 to 100
                double grade = getValidDouble(scanner, "Enter grade (0-100): ", 0, 100);

                // get weight 0.01 to 1 which is basically 1% to 100%
                double weight = getValidDouble(scanner, "Enter weight (e.g., 0.2 for 20%): ", 0.01, 1.0);

                totalWeightedScore += grade * weight; // weighted grade calculation
                totalWeight += weight; // add weight to total
            }

            // validate weight
            if (Math.abs(totalWeight - 1.0) > 0.001) {
                System.out.println(RED + "Error: Total weight must equal 1.0. Restart this student's data." + RESET);
                i--; // restart current student (I asked AI to make this because I didn't know how to)
                continue;
            }

            // calculating avg and pass status
            studentAverages[i] = totalWeightedScore;
            studentPassStatus[i] = studentAverages[i] >= 70; //You need atleast a 70 which is a C to pass
        }

        // report cards
        System.out.println(YELLOW + "\n--- Report Cards ---" + RESET);
        for (int i = 0; i < studentCount; i++) {
            System.out.println("\nStudent: " + studentNames[i]);
            System.out.printf("Average Score: %.2f\n", studentAverages[i]);

            // pass in green and fail in red
            if (studentPassStatus[i]) {
                System.out.println(GREEN + "Status: Pass" + RESET);
            } else {
                System.out.println(RED + "Status: Fail" + RESET);
            }
        }

        System.out.println(YELLOW + "\nThank you for using GradeMaster!" + RESET);
        scanner.close();
    }

    // gets a valid int 
    public static int getValidInt(Scanner scanner, String prompt, int min, int max) {
        int value;
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                value = scanner.nextInt();
                if (value >= min && value <= max) {
                    scanner.nextLine(); // deletes leftover newline (Searched up because I was confused on how to delete extra lines)
                    return value;
                }
            } else {
                scanner.next(); // clear invalid input
            }
            System.out.println(RED + "Invalid input. Please enter a number between " + min + " and " + max + "." + RESET);
        }
    }

    // gets a valid int
    public static int getValidInt(Scanner scanner, String prompt, int min) {
        return getValidInt(scanner, prompt, min, Integer.MAX_VALUE);
    }

    // gets a valid double
    public static double getValidDouble(Scanner scanner, String prompt, double min, double max) {
        double value;
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                value = scanner.nextDouble();
                if (value >= min && value <= max) {
                    scanner.nextLine(); // deletes leftover newline (Same as above)
                    return value;
                }
            } else {
                scanner.next(); // clear invalid input
            }
            System.out.println(RED + "Invalid input. Please enter a number between " + min + " and " + max + "." + RESET);
        }
    }

    // gets valid name that is letters only
    public static String getValidName(Scanner scanner, String prompt) {
        String name;
        while (true) {
            System.out.print(prompt);
            name = scanner.nextLine();
            if (name.matches("[a-zA-Z ]+")) { // allows letters and spaces (Searched up how to do this because I didn't know how to but it would enhance user experience)
                return name;
            }
            System.out.println(RED + "Invalid input. Name must contain letters only." + RESET);
        }
    }
}