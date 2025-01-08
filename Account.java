
public class Account {
    private String accountNumber;
    private double balance;
    private double interestRate;

    public Account(String accountNumber, double balance, double interestRate) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.interestRate = interestRate;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount + ". New Balance: " + balance);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew: " + amount + ". New Balance: " + balance);
        } else {
            System.out.println("Invalid withdrawal amount.");
        }
    }

    @Override
    public String toString() {
        return "Account[AccountNumber=" + accountNumber + ", Balance=" + balance + "]";
    }
}