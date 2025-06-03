package StringClass;
import java.util.Scanner;

public class AppTest {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String user;
        String pass;
        int count = 1;
        boolean flag = false;

        do { 
            System.out.print("Enter username: ");
            user = scanner.next();
            System.out.print("Enter password:");
            pass = scanner.next();
            if(count == 3){
                // flag = true;
                // break;
                // System.out.println("Sorry see you next time");
                break;
            }
            
            count++;
        } while (!(user.equals("dara") && pass.equals("dara@123")));
        
        if(user.equals("dara") && pass.equals("dara@123")) {
            System.out.println("Success!");
        }else {
            System.out.println("Wrong username or password");
        }
        // if(flag){
        //     System.out.println("Sorry see you next time");
        // }else{
        //     System.out.println("Welcome Dara");
        // }
    }
}