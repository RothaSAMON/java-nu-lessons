package HomeworkOne;
import java.util.Scanner;

public class Task1 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    System.out.print("Input 1st integer: ");
    int num1 = input.nextInt();

    System.out.print("Input 2nd integer: ");
    int num2 = input.nextInt();

    int sum = num1 + num2;
    int product = num1 * num2;
    double average = (double) (num1 + num2) / 2;
    int max = Math.max(num1, num2);
    int min = Math.min(num1, num2);

    System.out.println("Sum of two integers: " + sum);
    System.out.println("Product of two integers: " + product);
    System.out.printf("Average of two integers: %.2f\n", average);
    System.out.println("Max integer: " + max);
    System.out.println("Min integer: " + min);
}
}
