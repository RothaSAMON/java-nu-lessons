package Homework;
import java.util.Scanner;

public class Task3 {

    /**
     * Checks if a given password string is valid based on the following rules:
     * 1. A password must have at least eight characters.
     * 2. A password consists of only letters and digits.
     * 3. A password must contain at least two digits.
     *
     * @param password The string to validate.
     * @return true if the password is valid, false otherwise.
     */
    public static boolean isValidPassword(String password) {
        if (password == null) {
            return false;
        }

        // Rule 1: At least eight characters
        if (password.length() < 8) {
            System.out.println("Info: Password must have at least eight characters.");
            return false;
        }

        int digitCount = 0;
        for (char ch : password.toCharArray()) {
            // Rule 2: Consists of only letters and digits
            if (!Character.isLetterOrDigit(ch)) {
                System.out.println("Info: Password must consist of only letters and digits.");
                return false;
            }
            if (Character.isDigit(ch)) {
                digitCount++;
            }
        }

        // Rule 3: Must contain at least two digits
        if (digitCount < 2) {
            System.out.println("Info: Password must contain at least two digits.");
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Password rules:");
        System.out.println("1. A password must have at least eight characters.");
        System.out.println("2. A password consists of only letters and digits.");
        System.out.println("3. A password must contain at least two digits.");
        System.out.print("Input a password (You are agreeing to the above Terms and Conditions.): ");
        String passwordInput = scanner.nextLine();

        if (isValidPassword(passwordInput)) {
            System.out.println("Password is valid: " + passwordInput);
        } else {
            System.out.println("Password is not valid.");
        }

        scanner.close();
    }
}