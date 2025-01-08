public interface LoanManagement {
    void applyForLoan(Loan loan);
    void approveLoan(String loanId);
    void viewAllLoans();
    void viewLoans();
}