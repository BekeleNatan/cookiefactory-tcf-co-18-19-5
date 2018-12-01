package fr.unice.polytech.cod.order.states;

import fr.unice.polytech.cod.order.Item;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.recipe.Recipe;
import fr.unice.polytech.cod.recipe.ingredients.Ingredient;
import fr.unice.polytech.cod.store.CashRegister;
import fr.unice.polytech.cod.store.Stock;
import fr.unice.polytech.cod.store.workinghours.WorkingHours;

import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class OnCreation extends OrderState {

	private int minTimeToMakeOrder = 2;

	public OnCreation(Order order) {
		super(order);
	}

	public boolean nextState() {
		Order order = this.context;
		if(order.getCollectTime()!= null &&
			order.getCustomer().getPhoneNumber() != null &&
			order.items != null )
		{
			order.setCurrentState(new ToDo(context));
			return true;
		}
		return false;
	}

	public boolean addItem(Recipe recipe, int quantity, Stock stock){
		boolean canDoRecipe = true;
		for(Ingredient ingredient : recipe.getIngredients()){
			if(!stock.isEnough(ingredient,quantity)){
				canDoRecipe = false;
			}
		}
		if(canDoRecipe){
			for(Ingredient ingredient : recipe.getIngredients()){
				stock.removeIngredient(ingredient,quantity);
			}
			Item item = new Item(recipe, quantity);
			context.items.add(item);
			return true;
		}
		return false;
	}

	public boolean addInfos(Date collectTime, String phoneNumber, WorkingHours wh) {
		if(dateIsCorrect(collectTime, wh) && phoneNumberIsCorrect(phoneNumber)){
			this.context.setCollectTime(collectTime);
			this.context.getCustomer().setPhoneNumber(phoneNumber);
			return true;
		}
		return false;
	}

	private boolean phoneNumberIsCorrect(String phoneNumber) {
		return phoneNumber.matches("[0-9]*");
	}

	private boolean dateIsCorrect(Date collectTime, WorkingHours wo) {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(collectTime);

		Instant instant = Instant.ofEpochMilli(calendar.getTimeInMillis());
		LocalTime convert = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalTime();

		int dayOfTheWeek = calendar.get(Calendar.DAY_OF_WEEK)-1;
		if (dayOfTheWeek==0)dayOfTheWeek=7;

		calendar.add(calendar.HOUR,minTimeToMakeOrder);
		return collectTime.after(date) && wo.isOpenOn(DayOfWeek.of(dayOfTheWeek),convert);
	}

}