package fr.unice.polytech.cod.order.states;

import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.recipe.Recipe;
import fr.unice.polytech.cod.store.CashRegister;
import fr.unice.polytech.cod.store.Stock;
import fr.unice.polytech.cod.store.workinghours.WorkingHours;
import org.json.JSONObject;

import java.util.Date;

abstract public class OrderState {
	protected Order context;

	protected OrderState(Order order){
		if(order == null){
			throw new NullPointerException();
		}
		context = order;
	}

	abstract public boolean nextState();

	public void refuseState(){
		context.setCurrentState(new Refused(this.context));
	}

	// Default comportment of the states ( can be redifined )
	public boolean addItem(Recipe aRecipe, int aQuantity, Stock stock){
		return false;
	}

	public boolean addInfos(Date aDate, String aPhoneNumber, WorkingHours aWh) {
		return false;
	}

	public boolean collect(){
		return false;
	}

	public JSONObject showOrder(){
		JSONObject error = new JSONObject();
		error.put("status","error");
		return error;
	}
}