public class PartTimeEmployee extends Employee{
    private double hours;
    private double rates;
    public PartTimeEmployee() {
        super(0,"unknown");
        this.hours = 0;
        this.rates = 0;
    }
    public PartTimeEmployee(int id, String name, double hours, double rates) {
        super(id, name);
        this.hours = hours;
        this.rates = rates;
    }
    public PartTimeEmployee(int id, String name, Address address, double hours, double rates) {
        super(id, name, address);
        this.hours = hours;
        this.rates = rates;
    }
    public PartTimeEmployee(int id, String name, Address address, Date dateJoin, double hours,
            double rates) {
        super(id, name, address, dateJoin);
        this.hours = hours;
        this.rates = rates;
    }
    public double getHours() {
        return hours;
    }
    public void setHours(double hours) {
        this.hours = hours;
    }
    public double getRates() {
        return rates;
    }
    public void setRates(double rates) {
        this.rates = rates;
    }
    @Override
    public String toString() {
        return "PartTimeEmployee [hours=" + hours + ", rates=" + rates + ", getId()=" + getId() + ", getName()="
                + getName() + "]";

    
    }
    @Override
    public void display(){
        // super.display();
        System.out.println("ID: " + getId() + " Name: " + getName() + "Hours: " + getHours() + " Rates: " + getRates());
    }
   

    
    
}
