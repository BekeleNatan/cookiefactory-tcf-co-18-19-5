package order.states;

import order.Item;
import order.Order;
import recipe.Recipe;
import recipe.ingredients.Ingredient;
import store.Stock;
import store.workinghours.WorkingHours;

import java.time.*;
import java.util.*;

public class OnCreation extends OrderState {

	private int minTimeToMakeOrder = 2;

	public OnCreation(Order order) {
		super(order);
	}

	public boolean nextState() {
		Order order = this.context;
		context.setPrice(computePrice());
		if(order.getCollectTime()!= null &&
			order.getCustomer().getPhoneNumber() != null &&
			order.items.size() != 0  &&
			context.paymentConditionOk())
		{
			order.setCurrentState(new ToDo(context));
			return true;
		}
		return false;
	}

	private double computePrice() {
		// TODO compute price
		// attention c'est possible d'avoir 0 items
		return 0;
	}

	public boolean addItem(Recipe recipe, int quantity, Stock stock){
		if(recipe == null || quantity <= 0 || stock == null){
			return false;
		}

		boolean canDoRecipe = true;
		Map<Ingredient,Integer> ingredients = new HashMap<>();
		for(Ingredient ingredient : recipe.getIngredients()) {
			if(ingredients.get(ingredient)!=null){
				ingredients.put(ingredient,ingredients.get(ingredient)+quantity);
			}else{
				ingredients.put(ingredient,quantity);
			}
		}
		for(Ingredient ingredient : ingredients.keySet()){
			if(!stock.isEnough(ingredient,ingredients.get(ingredient))){
				canDoRecipe = false;
			}
		}
		if(canDoRecipe){
			for(Ingredient ingredient : ingredients.keySet()){
				stock.removeIngredient(ingredient,ingredients.get(ingredient));
			}
			Item item = new Item(recipe, quantity);
			context.items.add(item);
			return true;
		}
		return false;
	}

	public boolean addInfos(Date collectTime, String phoneNumber, WorkingHours wh) {
		if(collectTime == null || phoneNumber == null || wh == null){
			return false;
		}
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
		Calendar minTime = Calendar.getInstance();
		minTime.setTime(new Date());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(collectTime);

		Instant instant = Instant.ofEpochMilli(calendar.getTimeInMillis());
		LocalTime convert = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalTime();

		int dayOfTheWeek = calendar.get(Calendar.DAY_OF_WEEK)-1;
		if (dayOfTheWeek==0)dayOfTheWeek=7;

		minTime.add(calendar.HOUR,minTimeToMakeOrder);
		return collectTime.after(minTime.getTime()) && wo.isOpenOn(DayOfWeek.of(dayOfTheWeek),convert);
	}

}