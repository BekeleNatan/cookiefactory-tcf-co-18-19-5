package fr.unice.polytech.cod.store;

import fr.unice.polytech.cod.franchise.Franchise;
import fr.unice.polytech.cod.order.OrderRegister;
import fr.unice.polytech.cod.recipe.Recipe;
import fr.unice.polytech.cod.workinghours.WorkingHours;

import java.util.List;

public class Store {
	private String _storeName;
	private int _storeId;
	private int _taxeRate;
	private double _pointsToMoneyRate;
	public Franchise _owns;
	public Recipe _recipeOfTheMonth;
	public WorkingHours _opens_on;
	public StoreMenu _edit_and_show;
	public Stock _manages;
	public OrderRegister _use;

	public void setTaxeRate(int aNewRate) {
		this._taxeRate = aNewRate;
	}

	public int getTaxeRate() {
		return this._taxeRate;
	}

	public List<Recipe> getMenu(Stock aOurStock) {
		throw new UnsupportedOperationException();
	}

	public void getWorkingHours() {
		throw new UnsupportedOperationException();
	}

	public boolean setPointsToMoneyRate(double aNewPointsToEurosRate) {
		throw new UnsupportedOperationException();
	}

	public double getPointsToMoneyRate() {
		return this._pointsToMoneyRate;
	}
}