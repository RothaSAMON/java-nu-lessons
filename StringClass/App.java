package StringClass;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String user;
        String pass;
        int attempts = 0;
        
        do {
            System.out.print("Enter username: ");
            user = scanner.next();
            System.out.print("Enter password:");
            pass = scanner.next();
            
            if (!(user.equals("dara") && pass.equals("dara@123"))) {
                attempts++;
                System.out.println("Incorrect username or password. Attempts left: " + (3 - attempts));
            }
            
            if (attempts >= 3) {
                System.out.println("Too many incorrect attempts. Exiting program.");
                System.exit(0);
            }
            
        } while (!(user.equals("dara") && pass.equals("dara@123")));
        
        System.out.println("Welcome Dara");
        scanner.close();
    }
}