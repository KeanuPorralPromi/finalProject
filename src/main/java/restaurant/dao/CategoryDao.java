package restaurant.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import restaurant.entity.Category;

public interface CategoryDao extends JpaRepository<Category, Long> {

}
