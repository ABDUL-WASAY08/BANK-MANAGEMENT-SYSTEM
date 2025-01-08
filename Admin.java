import java.util.Scanner;

public abstract class Admin extends Person implements LoanManagement {
    private Bank bank;
    private String pinCode;

    public Admin(String name, String id, String contactInfo, String pinCode, Bank bank) {
        super(name, id, contactInfo);
        this.pinCode = pinCode;
        this.bank = bank;
    }

    public boolean verifyPin(String pin) {
        return this.pinCode.equals(pin);
    }

    public void hireEmployee(String name, String contactInfo, double salary, String pinCode) {
        String employeeId = "E" + String.format("%03d", bank.getEmployees().size() + 1);
        Employee newEmployee = new Employee(name, employeeId, contactInfo, salary, pinCode) {
            @Override
            public void searchCustomer(Scanner scanner, Employee employee) {
                // Implementation can be added later
            }
        };
        bank.addEmployee(newEmployee);
        System.out.println("Employee hired: " + name);
    }

    public void fireEmployee(String employeeId) {
        bank.removeEmployee(employeeId);
        System.out.println("Employee fired: ID = " + employeeId);
    }

    public void manageSalary(String employeeId, double newSalary) {
        Employee employee = bank.findEmployeeById(employeeId);
        if (employee != null) {
            employee.setSalary(newSalary); // Assuming a setSalary method exists
            System.out.println("Updated salary for employee: " + employee.getName());
        } else {
            System.out.println("Employee not found.");
        }
    }

    public void addBonus(String employeeId, double bonus) {
        Employee employee = bank.findEmployeeById(employeeId);
        if (employee != null) {
            employee.addBonus(bonus); // Assuming an addBonus method exists
            System.out.println("Bonus added to employee: " + employee.getName());
        } else {
            System.out.println("Employee not found.");
        }
    }

    @Override
    public void applyForLoan(Loan loan) {
        // Implementation can be added later
    }

    @Override
    public void approveLoan(String loanId) {
        bank.approveLoan(loanId);
        System.out.println("Loan approved: ID = " + loanId);
    }

    @Override
    public void viewAllLoans() {
        bank.displayAllLoans();
    }

    @Override
    public void viewLoans() {
        // Implementation can be added later
    }

    @Override
    public void displayDetails() {
        System.out.println("Admin Details: Name = " + name + ", ID = " + id);
    }

    public Bank getBank() {
        return bank;
    }
}
