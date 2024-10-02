package restaurant.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import restaurant.controller.model.FoodData;
import restaurant.entity.Food;

public interface FoodDao extends JpaRepository<Food, Long> {



}
