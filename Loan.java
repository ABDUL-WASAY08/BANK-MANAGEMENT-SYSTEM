
public class Loan {
    private String loanID;
    private double amount;
    private double interestRate;
    private int tenure;
    private boolean approved;

    public Loan(String loanID, double amount, double interestRate, int tenure) {
        this.loanID = loanID;
        this.amount = amount;
        this.interestRate = interestRate;
        this.tenure = tenure;
        this.approved = false;
    }

    public String getLoanID() {
        return loanID;
    }

    public void approveLoan() {
        approved = true;
    }

    @Override
    public String toString() {
        return "Loan[LoanID=" + loanID + ", Amount=" + amount + ", InterestRate=" + interestRate + ", Tenure=" + tenure + ", Approved=" + approved + "]";
    }
}

