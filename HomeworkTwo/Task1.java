package HomeworkTwo;

public class Task1 {

  // Task 1: Method to count words in a string
public static int countWords(String str) {
    if (str == null || str.isEmpty()) {
        return 0;
    }

    String[] words = str.trim().split("\\s+");
    return words.length;
}

public static void main(String[] args) {
    System.out.println("--- Task 1: Word Count ---");
    String inputString = "I love Java Programming Language";
    System.out.println("Input string: \"" + inputString + "\"");
    int wordCount = countWords(inputString);
    System.out.println("Number of words in the string: " + wordCount);

    String testString2 = "   Extra   spaces   everywhere  ";
    System.out.println("\nInput string: \"" + testString2 + "\"");
    System.out.println("Number of words in the string: " + countWords(testString2));

    String emptyString = "";
    System.out.println("\nInput string: \"" + emptyString + "\"");
    System.out.println("Number of words in the string: " + countWords(emptyString));

    String nullString = null;
    System.out.println("\nInput string: " + nullString);
    System.out.println("Number of words in the string: " + countWords(nullString));
    System.out.println("---------------------------");
}
}