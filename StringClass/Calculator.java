package StringClass;
import java.util.Scanner;

public class Calculator {
    private static int getNumber(String msg){
        Scanner scanner = new Scanner(System.in);
        int number;
        System.out.println(msg);
        number = scanner.nextInt();
        return number;
    }
    private static int addNumber(int number1, int number2){
        return number1+number2;
    }
    private static void show(String msg, int result){
        System.out.println(msg + result);
    }
    public static void main(String[] args) throws Exception {
        //Store
        int number1;
        int number2;
        int result;
        //Input
        number1 = getNumber("Enter number1: ");
        number2 = getNumber("Enter number2: ");
        //Process
        result = addNumber(number1,number2);
        //Output
        show("The result is ", result);

    }
}