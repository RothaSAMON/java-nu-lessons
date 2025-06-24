public class Employee {
    //data members
    private int id;
    private String name;
    private Address address; //aggregation
    private Date dateJoin; //composition
   
    //construtor
    public Employee() {
        id = 0;
        name = "unknown";
    }
    
    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Employee(int id, String name, Address address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
    public Employee(int id, String name, Address address, Date dateJoin) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.dateJoin = new Date(
            dateJoin.getDay(),
            dateJoin.getMonth(),
            dateJoin.getYear()
            );
    }
    //method members
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
   
    
    public void display(){
        System.out.println("ID: " + id + " Name: " + name);
    }
    
    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", address=" + address + ", dateJoin="
                + dateJoin + "]";
    }
   
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    

    
}
