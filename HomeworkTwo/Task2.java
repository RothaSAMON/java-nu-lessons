package HomeworkTwo;

import java.util.Scanner;

public class Task2 {

    // Function to show the menu
    public static void showMenu() {
        System.out.println("\nMENU");
        System.out.println("1: Add");
        System.out.println("2: Subtract");
        System.out.println("3: Multiply");
        System.out.println("4: Divide");
        System.out.println("5: Modulus");
        System.out.println("6: Exit");
        System.out.println("Enter your choice and then press Enter.");
        System.out.println("You will then be prompted to enter two numbers.");
    }

    // Function to add two numbers
    public static double add(double num1, double num2) {
        return num1 + num2;
    }

    // Function to subtract two numbers
    public static double subtract(double num1, double num2) {
        return num1 - num2;
    }

    // Function to multiply two numbers
    public static double multiply(double num1, double num2) {
        return num1 * num2;
    }

    // Function to divide two numbers
    public static double divide(double num1, double num2) {
        if (num2 == 0) {
            System.out.println("Error: Cannot divide by zero.");
            return Double.NaN;
        }
        return num1 / num2;
    }

    // Function to find the modulus of two numbers
    public static double modulus(double num1, double num2) {
        if (num2 == 0) {
            System.out.println("Error: Cannot perform modulus with zero divisor.");
            return Double.NaN;
        }
        return num1 % num2;
    }

    public static void main(String[] args) {
        System.out.println("--- Task 2: Calculator ---");
        Scanner scanner = new Scanner(System.in);
        char performAnotherOperation;

        do {
            showMenu();
            int choice = -1;

            // Get user choice and validate
            while (true) {
                System.out.print("Enter your choice (1-6): ");
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    if (choice >= 1 && choice <= 6) {
                        break;
                    } else {
                        System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next();
                }
            }

            if (choice == 6) {
                System.out.println("Exiting calculator. Goodbye!");
                break;
            }

            double num1 = 0, num2 = 0;
            boolean validInput = false;

            // Get first number
            while (!validInput) {
                System.out.print("Enter the first number: ");
                if (scanner.hasNextDouble()) {
                    num1 = scanner.nextDouble();
                    validInput = true;
                } else {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.next();
                }
            }

            validInput = false;
            // Get second number
            while (!validInput) {
                System.out.print("Enter the second number: ");
                if (scanner.hasNextDouble()) {
                    num2 = scanner.nextDouble();
                    validInput = true;
                } else {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.next();
                }
            }

            double result = 0;
            boolean calculationDone = true;

            switch (choice) {
                case 1:
                    result = add(num1, num2);
                    System.out.println("The result is " + result);
                    break;
                case 2:
                    result = subtract(num1, num2);
                    System.out.println("The result is " + result);
                    break;
                case 3:
                    result = multiply(num1, num2);
                    System.out.println("The result is " + result);
                    break;
                case 4:
                    result = divide(num1, num2);
                    if (!Double.isNaN(result)) { 
                        System.out.println("The result is " + result);
                    } else {
                        calculationDone = false;
                    }
                    break;
                case 5:
                    result = modulus(num1, num2);
                    if (!Double.isNaN(result)) {
                        System.out.println("The result is " + result);
                    } else {
                        calculationDone = false;
                    }
                    break;
            }

            if (choice >= 1 && choice <= 5 && calculationDone) {
                while (true) {
                    System.out.print("Do you want to perform another operation (Y or N)? ");
                    performAnotherOperation = scanner.next().toUpperCase().charAt(0);
                    if (performAnotherOperation == 'Y' || performAnotherOperation == 'N') {
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter 'Y' or 'N'.");
                    }
                }
                if (performAnotherOperation == 'N') {
                    System.out.println("Exiting calculator. Goodbye!");
                    break;
                }
            } else if (choice == 6) {
                performAnotherOperation = 'N';
            } else {
                performAnotherOperation = 'Y';
            }

        } while (performAnotherOperation == 'Y');

        scanner.close();
        System.out.println("---------------------------");
    }
}