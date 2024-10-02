package restaurant.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import restaurant.entity.Category;
import restaurant.entity.Restaurant;

@Data
@NoArgsConstructor
public class CategoryData {

	
	private Long categoryId;
	private String categoryName;
	
	private Set<RestaurantData> restaurants = new HashSet<>();

public CategoryData(Category category) {
	
	this.categoryId = category.getCategoryId();
	this.categoryName = category.getCategoryName();
	
	for(Restaurant restaurant : category.getRestaurants()) {
		this.restaurants.add(new RestaurantData(restaurant));
	}
}
public Category toCategory() {
	Category category = new Category();
	category.setCategoryId(categoryId);
	category.setCategoryName(categoryName);
	
	for(RestaurantData restaurant : restaurants) {
		category.getRestaurants().add(restaurant.toRestaurant());
	}
	
	return category;
}
}
