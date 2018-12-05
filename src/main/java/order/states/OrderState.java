package order.states;

import order.Order;
import recipe.Recipe;
import store.Stock;
import store.workinghours.WorkingHours;

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

}