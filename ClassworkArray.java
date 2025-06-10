import java.util.Scanner;

class ClassworkArray {
    public static void main(String[] args ){
        Scanner sc = new Scanner(System.in);
        final int SIZE = 100;
        int[] numbers = new int[SIZE];
        int count = 0;
        int choice;


        do {
            System.out.println("MENU");
            System.out.println("1.  ADD NUMBER");
            System.out.println("2. VIEW NUMBER");
            System.out.println("3. UPDATE NUMBER");
            System.out.println("4. DELETE NUMBER");
            System.out.println("5. SORT NUMBER");
            System.out.println("6. SEARCH NUMBER");
            System.out.println("0. EXIT");
            System.out.print("ENTER YOUR CHOICE: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    if (count != numbers.length) {
                        System.out.print("Enter number: ");
                        numbers[count++] = sc.nextInt();
                    }
                    else {
                        System.out.println("Array is full");
                    }
                }
                case 2 -> {
                    if (count != 0) {
                        for (int i = 0; i < count; i++) {
                            System.out.println("item: " + numbers[i]);
                        }
                    }
                    else
                        System.out.println("Array is empty");
                }
                case 3 -> {
                    System.out.print("Enter number to update: ");
                    int addNumber = sc.nextInt();
                    for (int i = 0; i < count; i++) {
                        if (addNumber == numbers[i]) {
                            System.out.println("Number Found");
                            System.out.print("Enter number to update: ");
                            numbers[i] = sc.nextInt();
                            System.out.println("Number updated Successfully");
                            break;
                        } else
                            System.out.println("Number doesn't exist");
                        break;
                    }
                }
                case 4 -> {
                    System.out.print("Enter number to delete: ");
                    int deleteNumber = sc.nextInt();
                    for (int i = 0; i < count; i++) {
                        if (deleteNumber == numbers[i]) {
                            for (int j = i; j < count - 1; j++) {
                                numbers[j] = numbers[j + 1];
                            }
                        }
                    }
                    count--;
                    System.out.println("Number deleted successfully");
                }
                case 5 -> {
                    for (int pass = 0; pass < count - 1; pass++) {
                        for (int i = 0; i < count - 1; i++) {
                            if (numbers[i] > numbers[i + 1]) {
                                int temp = numbers[i];
                                numbers[i] = numbers[i + 1];
                                numbers[i + 1] = temp;
                            }
                        }
                    }
                    System.out.println("Number Sorted Successfully");
                }
                case 6 -> {
                    System.out.print("Enter number to search: ");
                    int searchNumber = sc.nextInt();
                    boolean found = false;
                    for (int i = 0; i < count; i++) {
                        if (searchNumber == numbers[i]) {
                            System.out.println("Number " + searchNumber + " Found at index " + "[ " + i + " ]");
                            found = true;
                        }
                    }
                    if (!found) // outside of the loop cuz it gon print a bunch of time
                        System.out.println("Number doesn't exist");
                }
                default -> System.out.println("Invalid choice");
            }
        }while (choice!=0);




    }
}