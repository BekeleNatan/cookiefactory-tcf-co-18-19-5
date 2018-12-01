package fr.unice.polytech.cod.order;

public class Customer {
	private String phoneNumber = null;

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public boolean sendMessage(String aContent) {
		throw new UnsupportedOperationException();
	}

	public void setPhoneNumber(String phone) {
		phoneNumber = phone;
	}
}