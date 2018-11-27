package fr.unice.polytech.cod.order;

public class Customer {
	private String _phoneNumber;
	public Order _is_made_by;

	public String getPhoneNumber() {
		return this._phoneNumber;
	}

	public boolean sendMessage(String aContent) {
		throw new UnsupportedOperationException();
	}

	public boolean payBack(String aTransactionId) {
		throw new UnsupportedOperationException();
	}
}