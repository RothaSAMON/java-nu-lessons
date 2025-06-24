// public class App {
//     public static void main(String[] args) throws Exception {
//         Address address1 = new Address("PP", "CAMBODIA");
//         Date dateJoin = new Date(10,10,2010);
//         Employee employee1 = new Employee(101,"Dara",address1,dateJoin);
//         //System.out.println(employee1);
//         PartTimeEmployee ptEmployee = new PartTimeEmployee(103,"Bora",10,20);
//         //System.out.println(ptEmployee);

//         FullTimeEmployee ftEmployee = new FullTimeEmployee(102, "Lina", 500.0);
//         //System.out.println(ftEmployee);

//         employee1 = ptEmployee;
//         employee1 = ftEmployee;
//         //System.out.println(employee1);
//         employee1.display();


        
       

//     }
// }

public class App {
    public static void main(String[] args) throws Exception {
        Address address1 = new Address("PP", "CAMBODIA");
        Date dateJoin = new Date(10,10,2010);
        Employee employee1 = new Employee(101,"Dara",address1,dateJoin);
        //System.out.println(employee1);
        PartTimeEmployee ptEmployee = new PartTimeEmployee(103,"Bora",10,20);
        //System.out.println(ptEmployee);

        FullTimeEmployee ftEmployee = new FullTimeEmployee(102, "Lina", 500.0);
        //System.out.println(ftEmployee);

        employee1 = ptEmployee;
        //employee1 = ftEmployee;
        //System.out.println(employee1);
        employee1.display();

        Employee employee2 = new FullTimeEmployee(104, "Kaka", 500.0);

        if(employee1 instanceof FullTimeEmployee){
            System.out.println("object is fulltime");
        }else{
            System.out.println("object is parttime");
        }
       

    }
}