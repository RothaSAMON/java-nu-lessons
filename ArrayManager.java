import java.util.Scanner;

public class ArrayManager {

    static final int max_size = 100;
    static int[] numbers = new int[max_size];
    static int currentCount = 0;
    static Scanner scanner = new Scanner(System.in);

    public static void displayMenu() {
        System.out.println("\n====>>> Menu <<<====");
        System.out.println("1. Add number(s)");
        System.out.println("2. View numbers");
        System.out.println("3. Update number");
        System.out.println("4. Delete number");
        System.out.println("5. Search number");
        System.out.println("6. Sort numbers");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }

    public static void addNumbers() {
        System.out.print("Enter how many numbers to add: ");
        int n = scanner.nextInt();
        if (currentCount + n > max_size) {
            System.out.println("Cannot add " + n + " numbers. Array will exceed maximum size.");
            System.out.println("You can add at most " + (max_size - currentCount) + " more numbers.");
            return;
        }
        for (int i = 0; i < n; i++) {
            if (currentCount < max_size) {
                System.out.print("Enter number " + (i + 1) + ": ");
                numbers[currentCount++] = scanner.nextInt();
            } else {
                System.out.println("Array is full. Cannot add more numbers.");
                break;
            }
        }
        System.out.println(n + " number(s) added successfully.");
    }

    public static void viewNumbers() {
        if (currentCount == 0) {
            System.out.println("No numbers to display.");
        } else {
            System.out.println("--- Numbers in Array ---");
            for (int i = 0; i < currentCount; i++) {
                System.out.print(numbers[i] + " ");
            }
            System.out.println("\n------------------------");
        }
    }

    public static void updateNumber() {
        if (currentCount == 0) {
            System.out.println("No numbers to update.");
            return;
        }
        System.out.print("Enter the number to update: ");
        int oldNum = scanner.nextInt();
        boolean found = false;
        for (int i = 0; i < currentCount; i++) {
            if (numbers[i] == oldNum) {
                System.out.print("Enter the new number: ");
                numbers[i] = scanner.nextInt();
                System.out.println("Number updated successfully!");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Number not found in the array.");
        }
    }

    public static void deleteNumber() {
        if (currentCount == 0) {
            System.out.println("No numbers to delete.");
            return;
        }
        System.out.print("Enter the number to delete: ");
        int numToDelete = scanner.nextInt();
        boolean found = false;
        for (int i = 0; i < currentCount; i++) {
            if (numbers[i] == numToDelete) {
                for (int j = i; j < currentCount - 1; j++) {
                    numbers[j] = numbers[j + 1];
                }
                numbers[currentCount - 1] = 0;
                currentCount--;
                System.out.println("Number deleted successfully!");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Number not found in the array.");
        }
    }

    public static void searchNumber() {
        if (currentCount == 0) {
            System.out.println("No numbers to search.");
            return;
        }
        System.out.print("Enter the number to search: ");
        int numToSearch = scanner.nextInt();
        boolean found = false;
        for (int i = 0; i < currentCount; i++) {
            if (numbers[i] == numToSearch) {
                System.out.println("Number " + numToSearch + " found at index " + i + ".");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Number " + numToSearch + " not found in the array.");
        }
    }

    public static void sortNumbers() {
        if (currentCount == 0) {
            System.out.println("No numbers to sort.");
            return;
        }
        if (currentCount == 1) {
            System.out.println("Only one number in the array. Already sorted.");
            viewNumbers();
            return;
        }

        System.out.println("Sort Options:");
        System.out.println("1. Ascending (Small to Big)");
        System.out.println("2. Descending (Big to Small)");
        System.out.print("Choose sort order: ");
        int sortChoice = scanner.nextInt();

        for (int i = 0; i < currentCount - 1; i++) {
            for (int j = 0; j < currentCount - i - 1; j++) {
                boolean shouldSwap = false;
                if (sortChoice == 1 && numbers[j] > numbers[j + 1]) { 
                    shouldSwap = true;
                } else if (sortChoice == 2 && numbers[j] < numbers[j + 1]) {
                    shouldSwap = true;
                }

                if (shouldSwap) {
                    int temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                }
            }
        }
        System.out.println("Numbers sorted successfully.");
        viewNumbers();
    }

    public static void main(String[] args) {
        int choice;

        do {
            displayMenu();
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number for the choice.");
                scanner.next();
                System.out.print("Enter choice: ");
            }
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addNumbers();
                    break;
                case 2:
                    viewNumbers();
                    break;
                case 3:
                    updateNumber();
                    break;
                case 4:
                    deleteNumber();
                    break;
                case 5:
                    searchNumber();
                    break;
                case 6:
                    sortNumbers();
                    break;
                case 0:
                    System.out.println("Exiting program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 0);

        scanner.close();
    }
}