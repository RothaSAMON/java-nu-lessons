public class DisplayDays {

    public static void main(String[] args) {
        // We will iterate through the numbers for which specific output is required.
        int[] dayNumbers = {1, 3, 6};

        for (int day : dayNumbers) {
            switch (day) {
                case 1:
                    System.out.println("1- Sunday");
                    break;
                case 3:
                    System.out.println("3. Wednesday");
                    break;
                case 6:
                    System.out.println("6. Saturaday"); // Using the spelling "Saturaday" as requested
                    break;
                // No default case is strictly needed here since we are only iterating
                // through the numbers 1, 3, and 6, for which we have defined cases.
                // If other numbers were possible, a default case would be good practice:
                // default:
                //     System.out.println(day + " - No specific output defined for this day.");
                //     break;
            }
        }
    }
}