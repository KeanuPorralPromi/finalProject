package restaurant.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import restaurant.entity.Food;
import restaurant.entity.Restaurant;

@Data
@NoArgsConstructor
public class FoodData {

	private Long foodId;
	private String foodName;
	private Set<FoodRestaurantData> restaurants = new HashSet<>();

public FoodData(Food food) {
	
	this.foodId = food.getFoodId();
	this.foodName = food.getFoodName();
	
	for(Restaurant restaurant : food.getRestaurants()) {
		this.restaurants.add(new FoodRestaurantData(restaurant));
	
	}
}
public Food toFood() { 
	Food food = new Food();
	food.setFoodId(foodId);
	food.setFoodName(foodName);
	
	for(FoodRestaurantData foodRestaurantData : restaurants) {
		food.getRestaurants().add(foodRestaurantData.foodRestaurantDataToRestaurant());
	}
	
	return food;

}
@Data
@NoArgsConstructor
public static class FoodRestaurantData {
	private Long restaurantId;
	private String restaurantName;
	private String restaurantAddress;
	private Long restaurantRating;

public FoodRestaurantData(Restaurant restaurant) {
	
	this.restaurantId = restaurant.getRestaurantId();
	this.restaurantName = restaurant.getRestaurantName();
	this.restaurantAddress = restaurant.getRestaurantAddress();
	this.restaurantRating = restaurant.getRestaurantRating();
}
public Restaurant foodRestaurantDataToRestaurant() {
	Restaurant restaurant = new Restaurant();
	restaurant.setRestaurantId(restaurantId);
	restaurant.setRestaurantName(restaurantName);
	restaurant.setRestaurantAddress(restaurantAddress);
	restaurant.setRestaurantRating(restaurantRating);
	
	return restaurant;
}
	
}



}

