import java.util.ArrayList;
import java.util.List;

public abstract class Customer extends Person implements AccountManagement, LoanManagement {
    private List<Account> accounts;
    private List<Loan> loans;
    private String pinCode;

    public Customer(String name, String id, String contactInfo, String pinCode) {
        super(name, id, contactInfo);
        this.accounts = new ArrayList<>();
        this.loans = new ArrayList<>();
        this.pinCode = pinCode;
    }

    public boolean verifyPin(String pin) {
        return this.pinCode.equals(pin);
    }

    @Override
    public void openAccount(Account account) {
        accounts.add(account);
        System.out.println("Account opened: " + account);
    }

    @Override
    public void closeAccount(String accountNumber) {
        accounts.removeIf(acc -> acc.getAccountNumber().equals(accountNumber));
        System.out.println("Account closed: " + accountNumber);
    }

    @Override
    public void applyForLoan(Loan loan) {
        loans.add(loan);
        System.out.println("Loan application submitted: " + loan);
    }

    @Override
    public void approveLoan(String loanId) {
        // Implementation can be added later
    }

    @Override
    public void listAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    @Override
    public void viewAllLoans() {
        for (Loan loan : loans) {
            System.out.println(loan);
        }
    }

    @Override
    public void viewLoans() {

    }

    @Override
    public void displayDetails() {
        System.out.println("Customer Details: Name = " + name + ", ID = " + id + ", Contact = " + contactInfo);
    }

    public Account getAccountByNumber(String accountNumber) {
        return accounts.stream().filter(acc -> acc.getAccountNumber().equals(accountNumber)).findFirst().orElse(null);
    }
}