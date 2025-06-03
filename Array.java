import java.util.Scanner;
public class Array {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int size = 100;
        int[] num = new int[size];
        int count = 0;
        int choice;

        do {
            System.out.println("====>>> Menu <<<====");
            System.out.println("1. Add number");
            System.out.println("2. View numbers");
            System.out.println("3. Update number");
            System.out.println("4. Delete number");
            System.out.println("5. Search number");
            System.out.println("6. Sort number");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter how many numbers: ");
                    int n = scanner.nextInt();
                    for (int i = 0; i < n; i++) {
                        System.out.print("Enter number: ");
                        num[count++] = scanner.nextInt();
                    }
                }
                case 2 -> {
                    if (count == 0) {
                        System.out.println("No numbers to display.");
                    } else {
                        for (int i = 0; i < count; i++) {
                            System.out.println("Number: " + num[i]);
                        }
                    }
                }
                case 3 -> {
                    System.out.print("Enter number to update: ");
                    int Up_num = scanner.nextInt();
                    boolean found = false;

                    for (int i = 0; i < count; i++) {
                        if (num[i] == Up_num) {
                            System.out.print("Enter new number: ");
                            num[i] = scanner.nextInt();
                            System.out.println("Updated Successfully...!!");
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("Number not found...!!");
                    }
                }
                case 4 -> {

                    System.out.print("Enter number to delete: ");
                    int Del_num = scanner.nextInt();
                    boolean found = false;

                    for (int i = 0; i < count; i++) {
                        if (num[i] == Del_num) {
                            for (int j = i; j < count - 1; j++) {
                                num[j] = num[j + 1];
                            }
                            count--;
                            found = true;
                            System.out.println("Deleted Successfully...!!");
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("Number not found...!!");
                    }
                }
                case 5 -> {
                    System.out.print("Enter number to search: ");
                    int S_num = scanner.nextInt();
                    boolean found = false;

                    for (int i = 0; i < count; i++) {
                        if (num[i] == S_num) {
                            System.out.println("Search Successfully....!!");
                            System.out.println("Number found: " + num[i]);
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("Number not found.......!!!!!!");
                    }
                }
                case 6 -> {
                    if (count == 0) {
                        System.out.println("No numbers to sort!");
                        break;
                    }

                    System.out.println("1. From big to small.");
                    System.out.println("2. From small to big.");
                    System.out.print("Choose option: ");
                    int op = scanner.nextInt();

                    for (int i = 0; i < count - 1; i++) {
                        for (int j = i + 1; j < count; j++) {
                            if ((op == 1 && num[i] < num[j]) || (op == 2 && num[i] > num[j])) {
                                int temp = num[i];
                                num[i] = num[j];
                                num[j] = temp;
                            }
                        }
                    }

                    System.out.println("Sorted Successfully....!!");
                    for (int i = 0; i < count; i++) {
                        System.out.println("Sorted number: " + num[i]);
                    }
                }
                case 0 -> {
                    System.out.println("Exited program successfully.....!");
                }
                default -> {
                    System.out.println("Invalid choice! Please try again.");
                }
            }

        } while (choice != 0);
    }
}
