package HomeworkOne;
import java.util.Scanner;

public class Task4 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    System.out.print("Input first number: ");
    int num1 = input.nextInt();

    System.out.print("Input second number: ");
    int num2 = input.nextInt();

    System.out.print("Input operator(+, -, *, /, %): ");
    char operator = input.next().charAt(0);

    switch (operator) {
        case '+':
            System.out.println(num1 + " + " + num2 + " = " + (num1 + num2));
            break;
        case '-':
            System.out.println(num1 + " - " + num2 + " = " + (num1 - num2));
            break;
        case '*':
            System.out.println(num1 + " * " + num2 + " = " + (num1 * num2));
            break;
        case '/':
            if (num2 != 0) {
                System.out.println(num1 + " / " + num2 + " = " + (num1 / num2));
            } else {
                System.out.println("Error: Cannot divide by zero.");
            }
            break;
        case '%':
            System.out.println(num1 + " % " + num2 + " = " + (num1 % num2));
            break;
        default:
            System.out.println("Invalid operator!");
    }
}
}
