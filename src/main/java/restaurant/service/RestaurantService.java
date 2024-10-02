package restaurant.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import restaurant.controller.model.CategoryData;
import restaurant.controller.model.FoodData;
import restaurant.controller.model.RestaurantData;
import restaurant.dao.CategoryDao;
import restaurant.dao.FoodDao;
import restaurant.dao.RestaurantDao;
import restaurant.entity.Category;
import restaurant.entity.Food;
import restaurant.entity.Restaurant;

@Service
public class RestaurantService {

	@Autowired 
	private CategoryDao categoryDao;
	
	@Autowired
	private RestaurantDao restaurantDao;
	
	@Autowired
	private FoodDao foodDao;
		//------------------------------CATEGORY
	@Transactional(readOnly = false)
	public CategoryData saveCategory(CategoryData categoryData) {
		Category category = categoryData.toCategory();
		Category dbCategory = categoryDao.save(category);
		
		return new CategoryData(dbCategory);
	}
	@Transactional(readOnly = true)
	public CategoryData retrieveCategoryById(Long categoryId) {
		Category category = findCategoryById(categoryId);
		
		return new CategoryData(category);
		
	}

	private Category findCategoryById(Long categoryId) {
		return categoryDao.findById(categoryId).orElseThrow(() -> new NoSuchElementException("Category with ID=" + categoryId + " does not exists"));
	
	} 
	@Transactional(readOnly = false )
	public void deleteCategoryById(Long categoryId) {
		Category category = findCategoryById(categoryId);
		
		categoryDao.delete(category);
		
		
		
		
		//------------------------------RESTAURANT 

		

	}
	@Transactional(readOnly = false)
	public RestaurantData saveRestaurant(RestaurantData restaurantData, Long categoryId) {
		Category category = findCategoryById(categoryId);
		
		Restaurant restaurant = restaurantData.toRestaurant();
		
		
		restaurant.setCategory(category);
		category.getRestaurants().add(restaurant);
		
		Restaurant dbRestaurant = restaurantDao.save(restaurant);
		
	   return new RestaurantData(dbRestaurant);
	}
	@Transactional(readOnly = true)
	public RestaurantData retrieveRestaurantById(Long categoryId, Long restaurantId) {
		findCategoryById(categoryId);
		Restaurant restaurant = findByRestaurantId(restaurantId);
		for(Food food : restaurant.getFoods()) {
		 food.getRestaurants().clear();
		}
		
		if(restaurant.getCategory().getCategoryId() != categoryId) {
			throw new IllegalStateException("Restaurant with ID=" + restaurantId + " is not owned by category with ID=" + categoryId);
		}
			
		return new RestaurantData(restaurant);
	}
 	
		private Restaurant findByRestaurantId(Long restaurantId) {
		
		return restaurantDao.findById(restaurantId).orElseThrow(() -> new NoSuchElementException("The restaurant with the ID=" + restaurantId + " does not exist."));
		
	}
		@Transactional(readOnly = false)
		public void deleteRestaurantById(Long restaurantId) {
			Restaurant restaurant = findByRestaurantId(restaurantId);
			restaurantDao.delete(restaurant);
			
	}
			
		
		//------------------------------FOOD
	@Transactional(readOnly = false)
	public FoodData saveFood(FoodData foodData) {
		Food food = foodData.toFood();
	
		Food dbFood = foodDao.save(food);
		for(Restaurant restaurant : dbFood.getRestaurants()) {
			restaurant.getFoods().add(dbFood);
			dbFood.getRestaurants().add(restaurant);
		}
		return new FoodData(dbFood);
	}
	@Transactional(readOnly = true)
	public FoodData retrieveFoodById(Long foodId) {
		Food food = findByFoodId(foodId);
		for(Restaurant restaurant : food.getRestaurants()) {
			restaurant.getFoods().add(food);
			food.getRestaurants().add(restaurant);
		}
		return new FoodData(food);
	}
	private Food findByFoodId(Long foodId) {
		return foodDao.findById(foodId).orElseThrow(() -> new NoSuchElementException("The food with the ID=" + foodId + " does not exist."));
	}
	@Transactional(readOnly = false)
	public void deleteFoodById(Long foodId) {
		foodDao.deleteById(foodId);
		
	}
	



}
