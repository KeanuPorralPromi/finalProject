package restaurant.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import restaurant.entity.Food;
import restaurant.entity.Restaurant;

@Data
@NoArgsConstructor
public class RestaurantData {
	
	private Long restaurantId;
	private String restaurantName;
	private String restaurantAddress;
	private Long restaurantRating;
	
	Set<FoodData> foods = new HashSet<>();
	

public RestaurantData(Restaurant restaurant) {
		
	this.restaurantId = restaurant.getRestaurantId();
	this.restaurantName = restaurant.getRestaurantName();
	this.restaurantAddress = restaurant.getRestaurantAddress();
	this.restaurantRating = restaurant.getRestaurantRating();

	for(Food food : restaurant.getFoods()) {
		this.foods.add(new FoodData(food));
	}
}
public Restaurant toRestaurant() {
	Restaurant restaurant = new Restaurant();
	
	restaurant.setRestaurantId(restaurantId);
	restaurant.setRestaurantName(restaurantName);
	restaurant.setRestaurantAddress(restaurantAddress);
	restaurant.setRestaurantRating(restaurantRating);
	
	for(FoodData foodData : foods) {
		restaurant.getFoods().add(foodData.toFood());
}
	
	return restaurant;

}
}