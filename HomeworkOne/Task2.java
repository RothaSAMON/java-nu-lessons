package HomeworkOne;
import java.util.Scanner;

public class Task2 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    System.out.print("Input a degree in Fahrenheit: ");
    double fahrenheit = input.nextDouble();

    double celsius = (fahrenheit - 32) * 5 / 9;

    System.out.printf("%.1f degree Fahrenheit is equal to %.1f in Celsius\n", fahrenheit, celsius);
}
}
