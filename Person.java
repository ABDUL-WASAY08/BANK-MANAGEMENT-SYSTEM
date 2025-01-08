import java.util.Scanner;

public abstract class Person {
    protected String name;
    protected String id;
    protected String contactInfo;

    public Person(String name, String id, String contactInfo) {
        this.name = name;
        this.id = id;
        this.contactInfo = contactInfo;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public abstract void displayDetails();
    public abstract void searchCustomer(Scanner scanner, Employee employee);
}