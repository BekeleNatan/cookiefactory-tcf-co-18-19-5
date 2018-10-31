package fr.unice.polytech.cod.order;

public class Customer {

    private String phoneNumber;
    private String message;

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
        message = content;
        System.out.println(content); //TODO figure out what to do
    }

    public Object getMessage() {
        return message;
    }
}