// public class App {
//     public static void main(String[] args) throws Exception {
//         System.out.println("Hello, World! From CM");
//     }
// }

// public class App {
//     public static void main(String[] args) throws Exception {
//         //while loop
//         int i=1;
//         while (i<=5) { 
//             System.out.println("Hello, World!");
//             i++;
//         }
//         //for loop
//         for(int j=1;j<=5;j++){
//             System.out.println("Hi Everyone");
//         }
//         //do while loop
//         int k=1;
//         do { 
//             System.out.println("welcome Everyone");
//             k++;
//         } while (k<=5);
//     }
// }


// import java.util.Scanner;

// public class App {
//     public static void main(String[] args) throws Exception {
//         Scanner scanner = new Scanner(System.in);
//         int pin;
//         do { 
//             System.out.println("Enter pin: ");
//             pin = scanner.nextInt();
//         } while (pin!=123);
//         System.out.println("Welcome");
//         scanner.close();

//     }
// }

/*  Java Week2  for Java Basics
    Author: Dara
    Date: 20/5/2025
    Rev. 1.0 */


    // ======================
//     import java.util.ArrayList;
//     import java.util.Scanner;
    
//     public class App {
//         public static void main(String[] args) {
//             Scanner scanner = new Scanner(System.in);
//             ArrayList<String> dataList = new ArrayList<>();
//             int choice;
//             do {
//                 System.out.println("Menu");
//                 System.out.println("1. Add");
//                 System.out.println("2. View");
//                 System.out.println("3. Update");
//                 System.out.println("4. Delete");
//                 System.out.println("0. Exit");
//                 System.out.print("Enter choice: ");
//                 choice = scanner.nextInt();
//                 scanner.nextLine();
//                 switch(choice){
//                     case 1:
//                         System.out.print("Enter data to add: ");
//                         String newData = scanner.nextLine();
//                         dataList.add(newData);
//                         System.out.println("Data added.");
//                         break;
//                     case 2:
//                         System.out.println("Viewing data:");
//                         for (String data : dataList) {
//                             System.out.println(data);
//                         }
//                         break;
//                     case 3:
//                         System.out.print("Enter index to update: ");
//                         int updateIndex = scanner.nextInt();
//                         scanner.nextLine();
//                         if (updateIndex >= 0 && updateIndex < dataList.size()) {
//                             System.out.print("Enter new data: ");
//                             String updatedData = scanner.nextLine();
//                             dataList.set(updateIndex, updatedData);
//                             System.out.println("Data updated.");
//                         } else {
//                             System.out.println("Invalid index.");
//                         }
//                         break;
//                     case 4:
//                         System.out.print("Enter index to delete: ");
//                         int deleteIndex = scanner.nextInt();
//                         if (deleteIndex >= 0 && deleteIndex < dataList.size()) {
//                             dataList.remove(deleteIndex);
//                             System.out.println("Data deleted.");
//                         } else {
//                             System.out.println("Invalid index.");
//                         }
//                         break;
//                     case 0:
//                         System.out.println("Exiting...");
//                         break;
//                     default:
//                         System.out.println("Invalid choice, please try again.");
//                         break;
//                 }
//             } while (choice != 0);
//             scanner.close();
//         }
//     }

// // ===========


// From Professor

import java.util.Scanner;
public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int choice;
        final int SIZE = 100;
        int[] numbers = new int[SIZE];
        int count = 0;
        do {
            System.out.println("Menu");
            System.out.println("1. Add number");
            System.out.println("2. View numbers");
            System.out.println("3. Update number");
            System.out.println("4. Delete number");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter number: ");
                    numbers[count++] = scanner.nextInt();
                    break;
                case 2:
                    for(int i=0;i<count;i++){
                        System.out.println("Number: " + numbers[i]);
                    }
                    break;
                case 3:
                    int uNumber;
                    System.out.println("Enter Updated numebr: ");
                    uNumber = scanner.nextInt();
                    for(int i=0;i<count;i++){
                        if(numbers[i] == uNumber){
                            System.out.println("Enter new number: ");
                            numbers[i] = scanner.nextInt();
                            break;
                        }
                    }
                    break;
                case 4:
                    int dNumber;
                    System.out.println("Enter deleted number: ");
                    dNumber = scanner.nextInt();
                    for(int i=0;i<count;i++){
                        if(numbers[i] == dNumber){
                            for(int j=i;j<count-1;j++){
                                numbers[j] = numbers[j+1];
                            }
                            count--;
                            break;
                        }
                    }
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid");
                    break;
            }
        }while (choice != 0);

    }

}