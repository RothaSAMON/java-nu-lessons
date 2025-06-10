package HomeworkTwo;
import java.util.Random;
import java.util.Scanner;

public class Task4 {
    public static void displayRandomMatrix(int n) {
        if (n <= 0) {
            System.out.println("Matrix dimension must be positive.");
            return;
        }

        Random random = new Random();
        int[][] matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = random.nextInt(2); 
            }
        }

        // Display the matrix
        System.out.println("Generated " + n + "-by-" + n + " matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
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
                scanner.next();
            }
        }


        displayRandomMatrix(n);
        scanner.close();
    }
}