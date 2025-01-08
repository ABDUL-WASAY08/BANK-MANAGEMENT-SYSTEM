import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class 1BankManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Bank bank = new Bank();
        Admin admin = new Admin("Admin", "A001", "admin@bank.com", "admin123", bank) {
            @Override
            public void searchCustomer(Scanner scanner, Employee employee) {

            }
        };
        System.out.println("\n\t\t\t\t\t\t\t\t**************************************");
        System.out.println("\t\t\t\t\t\t\t\tWelcome to the Bank Management System");
        System.out.println("\t\t\t\t\t\t\t\t**************************************\n\n");
        while (true) {
            System.out.println("************************");
            System.out.println("1.Sign Up as Customer  ");
            System.out.println("2.Login as Customer    ");
            System.out.println("3.Login as Employee    ");
            System.out.println("4.Login as Admin       ");
            System.out.println("5.Exit                 ");
            System.out.println("************************");
            System.out.println("==================");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    signUpCustomer(scanner, bank);
                    break;
                case 2:
                    customerLogin(scanner, bank);
                    break;
                case 3:
                    employeeLogin(scanner, bank);
                    break;
                case 4:
                    adminLogin(scanner, admin);
                    break;
                case 5:
                    System.out.println("****************************");
                    System.out.println("Exiting the system. Goodbye!");
                    System.out.println("****************************");
                    scanner.close();
                    return;
                default:
                    System.out.println("================================");
                    System.out.println("\nInvalid choice. Please try again.");
            }
        }
    }

    private static void signUpCustomer(Scanner scanner, Bank bank) {
        System.out.println("\nCustomer Sign-Up:");
        System.out.print("Enter Your Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Contact Info: ");
        String contactInfo = scanner.nextLine();
        System.out.print("Set Your PIN Code: ");
        String pinCode = scanner.nextLine();

        String customerId = "C" + String.format("%03d", bank.getCustomers().size() + 1);
        Person newCustomer = new Customer(name, customerId, contactInfo, pinCode) {
            @Override
            public void searchCustomer(Scanner scanner, Employee employee) {

            }
        };

        System.out.println("Your Customer ID is: " + customerId + ". Please wait for employee approval to activate your account.");
        bank.addPendingCustomer((Customer) newCustomer);
    }

    private static void customerLogin(Scanner scanner, Bank bank) {
        System.out.print("Enter Customer ID: ");
        String customerId = scanner.nextLine();
        System.out.print("Enter PIN Code: ");
        String customerPin = scanner.nextLine();

        Customer customer = bank.findCustomerById(customerId);
        if (customer != null && customer.verifyPin(customerPin)) {
            System.out.println("Login successful! Welcome, " + customer.getName());
            displayCustomerMenu(scanner, customer);
        } else {
            System.out.println("Invalid Customer ID or PIN.");
        }
    }

    private static void employeeLogin(Scanner scanner, Bank bank) {
        System.out.print("Enter Employee ID: ");
        String employeeId = scanner.nextLine();
        System.out.print("Enter PIN Code: ");
        String employeePin = scanner.nextLine();

        Employee employee = bank.findEmployeeById(employeeId);
        if (employee != null && employee.verifyPin(employeePin)) {
            System.out.println("Login successful! Welcome, " + employee.getName());
            displayEmployeeMenu(scanner, employee, bank);
        } else {
            System.out.println("Invalid Employee ID or PIN.");
        }
    }

    private static void adminLogin(Scanner scanner, Admin admin) {
        System.out.print("Enter Admin PIN: ");
        String adminPin = scanner.nextLine();

        if (admin.verifyPin(adminPin)) {
            System.out.println("Login successful! Welcome, Admin.");
            displayAdminMenu(scanner, admin);
        } else {
            System.out.println("Invalid Admin PIN.");
        }
    }


    private static void displayCustomerMenu(Scanner scanner, Customer customer) {
        while (true) {
            System.out.println("\nCustomer Menu:");
            System.out.println("1. Open Account");
            System.out.println("2. Close Account");
            System.out.println("3. View Accounts");
            System.out.println("4. Apply for Loan");
            System.out.println("5. Deposit Money");
            System.out.println("6. Withdraw Money");
            System.out.println("7. Check Balance");
            System.out.println("8. Logout");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    openAccount(scanner, customer);
                    break;
                case 2:
                    closeAccount(scanner, customer);
                    break;
                case 3:
                    viewAccounts(customer);
                    break;
                case 4:
                    applyForLoan(scanner, customer);
                    break;
                case 5:
                    depositMoney(scanner, customer);
                    break;
                case 6:
                    withdrawMoney(scanner, customer);
                    break;
                case 7:
                    checkBalance(scanner, customer);
                    break;
                case 8:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void openAccount(Scanner scanner, Customer customer) {
        System.out.print("Enter Account Number: ");
        String accNum = scanner.nextLine();
        System.out.print("Enter Initial Deposit: ");
        double deposit = scanner.nextDouble();
        System.out.print("Enter Interest Rate: ");
        double rate = scanner.nextDouble();

        customer.openAccount(new Account(accNum, deposit, rate));
        System.out.println("Account opened successfully!");
    }

    private static void closeAccount(Scanner scanner, Customer customer) {
        System.out.print("Enter Account Number to Close: ");
        String accNum = scanner.nextLine();
        customer.closeAccount(accNum);
        System.out.println("Account closed successfully!");
    }

    private static void viewAccounts(Customer customer) {
        System.out.println("Viewing all accounts:");
        customer.listAccounts(); // Assuming listAccounts() is implemented in the Customer class
    }

    private static void applyForLoan(Scanner scanner, Customer customer) {
        System.out.print("Enter Loan Amount: ");
        double loanAmount = scanner.nextDouble();
        System.out.print("Enter Interest Rate: ");
        double loanRate = scanner.nextDouble();
        System.out.print("Enter Tenure (months): ");
        int tenure = scanner.nextInt();

        String loanId = "L" + System.currentTimeMillis(); // Unique Loan ID
        Loan loan = new Loan(loanId, loanAmount, loanRate, tenure);
        customer.applyForLoan(loan);
        System.out.println("Loan application submitted. Loan ID: " + loanId);
    }

    private static void depositMoney(Scanner scanner, Customer customer) {
        System.out.print("Enter Account Number: ");
        String accNum = scanner.nextLine();
        System.out.print("Enter Amount to Deposit: ");
        double amount = scanner.nextDouble();

        Account account = customer.getAccountByNumber(accNum);
        if (account != null) {
            account.deposit(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void withdrawMoney(Scanner scanner, Customer customer) {
        System.out.print("Enter Account Number: ");
        String accNum = scanner.nextLine();
        System.out.print("Enter Amount to Withdraw: ");
        double amount = scanner.nextDouble();

        Account account = customer.getAccountByNumber(accNum); // Assuming a method to get account by number
        if (account != null) {
            account.withdraw(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void checkBalance(Scanner scanner, Customer customer) {
        System.out.print("Enter Account Number: ");
        String accNum = scanner.nextLine();

        Account account = customer.getAccountByNumber(accNum); // Assuming a method to get account by number
        if (account != null) {
            System.out.println("Current Balance: " + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }


    private static void displayEmployeeMenu(Scanner scanner, Employee employee, Bank bank) {
        while (true) {
            System.out.println("\nEmployee Menu:");
            System.out.println("1. Approve Customer Sign-Ups");
            System.out.println("2. Remove Customer");
            System.out.println("3. Edit Customer");
            System.out.println("4. Search Customer");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    approveCustomerSignUps(scanner, bank);
                    break;
                case 2:
                    removeCustomer(scanner, employee);
                    break;
                case 3:
                    editCustomer(scanner, employee);
                    break;
                case 4:
                    searchCustomer(scanner, employee);
                    break;
                case 5:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void approveCustomerSignUps(Scanner scanner, Bank bank) {
        System.out.println("Pending Customer Approvals:");
        List<Customer> pendingCustomers = bank.getPendingCustomers();

        if (pendingCustomers.isEmpty()) {
            System.out.println("No pending customer sign-ups.");
            return;
        }

        for (Customer pending : pendingCustomers) {
            System.out.println("Customer ID: " + pending.getId() + ", Name: " + pending.getName());
            System.out.print("Approve this customer? (yes/no): ");
            String approval = scanner.nextLine();
            if (approval.equalsIgnoreCase("yes")) {
                bank.addCustomer(pending);
                System.out.println("Customer approved: " + pending.getName());
            } else {
                System.out.println("Customer not approved: " + pending.getName());
            }
        }
        pendingCustomers.clear();
    }

    private static void removeCustomer(Scanner scanner, Employee employee) {
        System.out.print("Enter Customer ID to Remove: ");
        String customerId = scanner.nextLine();
        employee.removeCustomer(customerId);
        System.out.println("Customer removed successfully.");
    }

    private static void editCustomer(Scanner scanner, Employee employee) {
        System.out.print("Enter Customer ID to Edit: ");
        String customerId = scanner.nextLine();
        System.out.print("Enter New Name: ");
        String newName = scanner.nextLine();
        System.out.print("Enter New Contact Info: ");
        String newContactInfo = scanner.nextLine();

        employee.editCustomer(customerId, newName, newContactInfo);
        System.out.println("Customer details updated successfully.");
    }

    private static void searchCustomer(Scanner scanner, Employee employee) {
        System.out.print("Enter Customer ID to Search: ");
        String customerId = scanner.nextLine();
        employee.searchCustomer(customerId);
    }


    private static void displayAdminMenu(Scanner scanner, Admin admin) {
        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Hire Employee");
            System.out.println("2. Fire Employee");
            System.out.println("3. Manage Salary");
            System.out.println("4. Add Bonus");
            System.out.println("5. Approve Loan");
            System.out.println("6. View Loans");
            System.out.println("7. View Total Customers");
            System.out.println("8. View Total Employees");
            System.out.println("9. Logout");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    hireEmployee(scanner, admin);
                    break;
                case 2:
                    fireEmployee(scanner, admin);
                    break;
                case 3:
                    manageSalary(scanner, admin);
                    break;
                case 4:
                    addBonus(scanner, admin);
                    break;
                case 5:
                    approveLoan(scanner, admin);
                    break;
                case 6:
                    viewLoans(admin);
                    break;
                case 7:
                    System.out.println("Total Customers: " + admin.getBank().getTotalCustomers());
                    break;
                case 8:
                    System.out.println("Total Employees: " + admin.getBank().getTotalEmployees());
                    break;
                case 9:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void hireEmployee(Scanner scanner, Admin admin) {
        System.out.print("Enter Employee Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Contact Info: ");
        String contactInfo = scanner.nextLine();
        System.out.print("Set PIN Code: ");
        String pinCode = scanner.nextLine();
        System.out.print("Enter Salary: ");
        double salary = scanner.nextDouble();

        admin.hireEmployee(name, contactInfo, salary, pinCode);
        System.out.println("Employee hired successfully!");
    }

    private static void fireEmployee(Scanner scanner, Admin admin) {
        System.out.print("Enter Employee ID to Fire: ");
        String employeeId = scanner.nextLine();
        admin.fireEmployee(employeeId);
        System.out.println("Employee fired successfully!");
    }

    private static void manageSalary(Scanner scanner, Admin admin) {
        System.out.print("Enter Employee ID to Manage Salary: ");
        String employeeId = scanner.nextLine();
        System.out.print("Enter New Salary: ");
        double newSalary = scanner.nextDouble();

        admin.manageSalary(employeeId, newSalary);
        System.out.println("Salary updated successfully!");
    }

    private static void addBonus(Scanner scanner, Admin admin) {
        System.out.print("Enter Employee ID to Add Bonus: ");
        String employeeId = scanner.nextLine();
        System.out.print("Enter Bonus Amount: ");
        double bonus = scanner.nextDouble();

        admin.addBonus(employeeId, bonus);
        System.out.println("Bonus added successfully!");
    }

    private static void approveLoan(Scanner scanner, Admin admin) {
        System.out.print("Enter Loan ID to Approve: ");
        String loanId = scanner.nextLine();
        admin.approveLoan(loanId);
        System.out.println("Loan approved successfully!");
    }

    private static void viewLoans(Admin admin) {
        System.out.println("Viewing all loans:");
        admin.viewLoans();
    }
}
