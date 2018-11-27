package fr.unice.polytech.cod.store;

import fr.unice.polytech.cod.franchise.Franchise;
import fr.unice.polytech.cod.order.OrderRegister;
import fr.unice.polytech.cod.recipe.Recipe;
import fr.unice.polytech.cod.workinghours.WorkingHours;

import java.util.List;

public class Store {
	private String storeName;
	private int storeId;
	private int taxeRate;
	private double pointsToMoneyRate;
	public WorkingHours workingHours;
	public StoreMenu menu;
	public Stock stock;
	public OrderRegister orderRegister;

	public void setTaxeRate(int aNewRate) {
		this.taxeRate = aNewRate;
	}

	public int getTaxeRate() {
		return this.taxeRate;
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
		return this.pointsToMoneyRate;
	}
}