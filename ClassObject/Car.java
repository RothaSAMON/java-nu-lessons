// package ClassObject;

// public class Employee {
//   public static void main(String[] args) {
//     int id;
//     String name;
//     float salary;

//     void displayDetails() {
//       System.out.println("Employee ID: " + id);
//       System.out.println("Employee Name: " + name);
//       System.out.println("Employee Salary: $" + salary);
//       System.out.println("--------------------");
//   }
//   }
// }
package ClassObject;

public class Car {
  String make;
  String model;
  int year;

  // This is the constructor.
  // It has the same name as the class and no return type.
  public Car(String make, String model, int carYear) {
      System.out.println("Constructor is running! A new car is being built.");
      // Use 'this' to assign the passed-in values to the object's fields.
      this.make = make;
      this.model = model;
      this.year = carYear;
  }

  void displayInfo() {
      System.out.println("Car: " + this.year + " " + this.make + " " + this.model);
  }
}