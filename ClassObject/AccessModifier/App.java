package ClassObject.AccessModifier;

public class App {
  public static void main(String[] args) {
    System.out.println("--- Creating Employee 1 ---");
    Employee emp1 = new Employee();
    System.out.println("Employee 1 ID: " + emp1.getId());
    System.out.println("Employee 1 Name: " + emp1.getName());

    System.out.println("\n--- Creating Employee 2 ---");
    Employee emp2 = new Employee(101, "Jane Doe");
    System.out.println("Employee 2 ID: " + emp2.getId());
    System.out.println("Employee 2 Name: " + emp2.getName());

    System.out.println("\n--- Updating Employee 1 ---");
    emp1.setId(102);
    emp1.setName("John Smith");
    System.out.println("Employee 1 New ID: " + emp1.getId());
    System.out.println("Employee 1 New Name: " + emp1.getName());
  }
}