package HomeworkTwo;
import java.util.Random;
import java.util.Scanner;

public class Task5 {

    public static void playGame(Scanner scanner) {
        Random random = new Random();
        int numberToGuess = random.nextInt(6) + 1;
        int numberOfTries = 0;
        int guess;
        boolean hasGuessedCorrectly = false;

        System.out.println("\nGuess My Number Game");

        while (!hasGuessedCorrectly) {
            System.out.print("Enter a guess between 1 and 6: ");
            if (scanner.hasNextInt()) {
                guess = scanner.nextInt();
                numberOfTries++;

                if (guess < 1 || guess > 6) {
                    System.out.println("Invalid guess. Please enter a number between 1 and 6.");
                } else if (guess < numberToGuess) {
                    System.out.println("Too low! The random number is " + numberToGuess + " (for testing, actual game wouldn't show this yet)");
                } else if (guess > numberToGuess) {
                    System.out.println("Too high! The random number is " + numberToGuess + " (for testing, actual game wouldn't show this yet)");
                } else {
                    System.out.println("Correct! You got it in " + numberOfTries + " guesses!");
                    hasGuessedCorrectly = true;
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char playAgain;

        do {
            playGame(scanner);

            while (true) {
                System.out.print("Do you want to play (Y or N)? ");
                String input = scanner.next();
                if (input.length() > 0) {
                    playAgain = Character.toUpperCase(input.charAt(0));
                    if (playAgain == 'Y' || playAgain == 'N') {
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter 'Y' or 'N'.");
                    }
                } else {
                     System.out.println("Invalid input. Please enter 'Y' or 'N'.");
                }
            }
        } while (playAgain == 'Y');

        System.out.println("Thanks for playing!");
        scanner.close();
    }
}