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
    public boolean sendMessage(String content) {
        this.message = content;
        System.out.println(content); //TODO figure out what to do
        return true;
    }

    public boolean payBack(int transactionNumber){
        System.out.print(transactionNumber); //TODO figure out what to do
        return true;
    }

    public String getMessage() {
        return this.message;
    }

}