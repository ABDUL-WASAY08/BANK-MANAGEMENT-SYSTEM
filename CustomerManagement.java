public interface CustomerManagement {
    void removeCustomer(String customerId);
    void editCustomer(String customerId, String newName, String newContactInfo);
    void searchCustomer(String customerId);
}