public class Recipe {

	private Dough dough;
	private Flavour flavours;
	private Topping topping;
	private Cooking cooking;
	private Mix mix;
	private int price;

	public int getPrice() {
		return this.price;
	}

	/**
	 * 
	 * @param price
	 */
	public void setPrice(int price) {
		this.price = price;
	}

}