public abstract class Employee extends Person implements CustomerManagement {
    private double salary;
    private double bonus;
    private String pinCode;


    public Employee(String name, String id, String contactInfo, double salary, String pinCode) {
        super(name, id, contactInfo);
        this.salary =0;
        this.bonus = 0;
        this.pinCode = pinCode;
    }

    public boolean verifyPin(String pin) {
        return this.pinCode.equals(pin);
    }

    @Override

    public void removeCustomer(String customerId) {
        System.out.println("Customer removed: ID = " + customerId);
    }

    @Override
    public void editCustomer(String customerId, String newName, String newContactInfo) {
        System.out.println("Customer edited: ID = " + customerId + ", New Name = " + newName + ", New Contact Info = " + newContactInfo);
    }

    @Override
    public void searchCustomer(String customerId) {
        System.out.println("Searching for customer: ID = " + customerId);
    }

    @Override
    public void displayDetails() {
        System.out.println("Employee Details: Name = " + name + ", ID = " + id + ", Contact = " + contactInfo + ", Salary = " + salary);
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void addBonus(double bonus) {
        this.bonus += bonus;
    }
}