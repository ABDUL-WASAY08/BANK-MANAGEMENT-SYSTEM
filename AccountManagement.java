
public interface AccountManagement {
    void openAccount(Account account);
    void closeAccount(String accountNumber);
    void listAccounts();
    Account getAccountByNumber(String accountNumber); // New method to get account by number
}