package ClassObject;

public class App {
  public static void main(String[] args) {
      // 1. Create and initialize the object in a single line!
      // The values ("Ford", "Mustang", 1969) are passed to the constructor.
      Car car1 = new Car("Ford", "Mustang", 1969);

      // Create another, different object just as easily.
      Car car2 = new Car("Tesla", "Model 3", 2023);

      // The objects are ready to be used immediately.
      car1.displayInfo();
      car2.displayInfo();
  }
}