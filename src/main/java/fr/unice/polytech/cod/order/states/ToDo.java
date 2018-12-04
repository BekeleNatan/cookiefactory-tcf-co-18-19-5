package fr.unice.polytech.cod.order.states;

import fr.unice.polytech.cod.order.Item;
import fr.unice.polytech.cod.order.Order;
import org.json.JSONObject;

public class ToDo extends OrderState {

	public ToDo(Order order) {
		super(order);
	}

	public boolean nextState(){
		context.setCurrentState(new Done(context));
		return true;
	}

}