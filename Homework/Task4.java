package Homework;
import java.util.Random;
import java.util.Scanner;

public class Task4 {

    /**
     * Displays an n-by-n matrix with random binary numbers (0 or 1).
     *
     * @param n The dimension of the square matrix.
     */
    public static void displayRandomMatrix(int n) {
        if (n <= 0) {
            System.out.println("Matrix dimension must be positive.");
            return;
        }

        Random random = new Random();
        int[][] matrix = new int[n][n];

        // Populate the matrix with random 0s or 1s
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = random.nextInt(2); // Generates 0 or 1
            }
        }

        // Display the matrix
        System.out.println("Generated " + n + "-by-" + n + " matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println(); // New line after each row
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = 0;
        boolean validInput = false;

        while(!validInput) {
            System.out.print("Input a number for the matrix dimension (n): ");
            if (scanner.hasNextInt()) {
                n = scanner.nextInt();
                if (n > 0) {
                    validInput = true;
                } else {
                    System.out.println("Please enter a positive integer for the dimension.");
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next(); // Consume the invalid input
            }
        }


        displayRandomMatrix(n);
        scanner.close();
    }
}