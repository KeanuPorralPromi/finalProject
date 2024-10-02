package restaurant.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import restaurant.controller.model.CategoryData;
import restaurant.controller.model.FoodData;
import restaurant.controller.model.RestaurantData;
import restaurant.service.RestaurantService;

@RestController
@RequestMapping("/restaurants")
@Slf4j
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;
	//------------------CATEGORY
@PostMapping("/category")
@ResponseStatus(code = HttpStatus.CREATED)
public CategoryData insertCategory(@RequestBody CategoryData categoryData) {
	log.info("Creating category {}", categoryData);
	return restaurantService.saveCategory(categoryData);
	
}

@GetMapping("/category/{categoryId}")
public CategoryData retrieveCategoryById(@PathVariable Long categoryId) {
 log.info("Retrieving category {}", categoryId);
 return restaurantService.retrieveCategoryById(categoryId);
}
@DeleteMapping("/category/{categoryId}")
public Map<String, String> deleteCategoryById(@PathVariable Long categoryId){
	log.info("Deleting category with ID={}", categoryId);
	restaurantService.deleteCategoryById(categoryId);
	return Map.of("message", "Deletion of category with ID=" + categoryId + " was succesful.");
}
	//-------------------RESTAURANT
@PostMapping("/category/{categoryId}/restaurant")
@ResponseStatus(code = HttpStatus.CREATED)
public RestaurantData insertRestaurant(@RequestBody RestaurantData restaurantData, @PathVariable Long categoryId) {
	log.info("Creating restaurant{} for category {})", restaurantData, categoryId);
	return restaurantService.saveRestaurant(restaurantData, categoryId);
}
@PutMapping("/category/{categoryId}/restaurant/{restaurantId}")
public RestaurantData updateOnlyRestaurant(@PathVariable Long categoryId, @PathVariable Long restaurantId, @RequestBody RestaurantData restaurantData) {
	restaurantData.setRestaurantId(restaurantId);
	log.info("Updating restaurant {}", restaurantData);
	return restaurantService.saveRestaurant(restaurantData, categoryId);
}
@GetMapping("/category/{categoryId}/restaurant/{restaurantId}")
public RestaurantData retrieveRestaurantById(@PathVariable Long categoryId, @PathVariable Long restaurantId) {
	log.info("Retrieving restaurant {} for category {}", restaurantId);
	return restaurantService.retrieveRestaurantById(categoryId, restaurantId);
}
@DeleteMapping("/restaurant/{restaurantId}")
public Map<String, String> deleteRestaurantById(@PathVariable Long restaurantId) {
	log.info("Deleting restaurant ID={}", restaurantId);
	
	restaurantService.deleteRestaurantById(restaurantId);
	
	return Map.of("message", "Deletion of restaurnt with ID=" + restaurantId + " was succesful.");

}
	//--------------------FOOD
@PostMapping("/food")
@ResponseStatus(code = HttpStatus.CREATED)
public FoodData insertFood(@RequestBody FoodData foodData) {
	log.info("Creating food {}", foodData);
	return restaurantService.saveFood(foodData);
}
@GetMapping("/food/{foodId}")
public FoodData retrieveFoodById(@PathVariable Long foodId) {
	log.info("Retrieving Food {}", foodId);
	return restaurantService.retrieveFoodById(foodId);
}
@PutMapping("/food/{foodId}")
public FoodData updateFoodById(@PathVariable Long foodId, @RequestBody FoodData foodData) {
	foodData.setFoodId(foodId);
	log.info("Updating food {}", foodId);
	return restaurantService.saveFood(foodData);
}
@DeleteMapping("/food/{foodId}")
public Map<String, String> deleteFoodById(@PathVariable Long foodId) {
	log.info("Deleting Food ID=", foodId);
	
	restaurantService.deleteFoodById(foodId);
	
	return Map.of("message", "Deletion of food with ID=" + foodId + " was succesful.");
}
}



 



