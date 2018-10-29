package fr.unice.polytech.cod.order;

public class Customer {

    private String phoneNumber;

    public Customer(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * @param content
     */
    public void sendMessage(String content) {
        // TODO - implement Customer.sendMessage
        throw new UnsupportedOperationException();
    }

}