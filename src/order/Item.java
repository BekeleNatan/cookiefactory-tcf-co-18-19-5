package order;

public class Item {

	private int quantity;
	private int price;

	/**
	 * 
	 * @param recipe
	 * @param quantity
	 */
	public Item(Recipe recipe, int quantity) {
		// TODO - implement Item.Item
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public Recipe getRecipe() {
		// TODO - implement Item.getRecipe
		throw new UnsupportedOperationException();
	}

	public int getPrice() {
		return this.price;
	}

}