import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Customer> customers;
    private List<Employee> employees;
    private List<Loan> loanRequests;
    private List<Customer> pendingCustomers;

    public Bank() {
        customers = new ArrayList<>();
        employees = new ArrayList<>();
        loanRequests = new ArrayList<>();
        pendingCustomers = new ArrayList<>();
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Customer> getPendingCustomers() {
        return pendingCustomers;
    }

    public void addPendingCustomer(Customer customer) {
        pendingCustomers.add(customer);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void removeEmployee(String employeeId) {
        employees.removeIf(emp -> emp.getId().equals(employeeId));
    }

    public Employee findEmployeeById(String employeeId) {
        for (Employee emp : employees) {
            if (emp.getId().equals(employeeId)) {
                return emp;
            }
        }
        return null;
    }

    public void approveLoan(String loanId) {
        for (Loan loan : loanRequests) {
            if (loanId.equals(loan.getLoanID())) {
                loan.approveLoan();
                return;
            }
        }
    }

    public void displayAllLoans() {
        for (Loan loan : loanRequests) {
            System.out.println(loan);
        }
    }

    public Customer findCustomerById(String customerId) {
        return customers.stream().filter(c -> c.getId().equals(customerId)).findFirst().orElse(null);
    }

    public int getTotalCustomers() {
        return customers.size();
    }

    public int getTotalEmployees() {
        return employees.size();
    }
}