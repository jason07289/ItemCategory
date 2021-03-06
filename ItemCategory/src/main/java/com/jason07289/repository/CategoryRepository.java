package com.jason07289.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jason07289.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	Optional<Category> findById (Long categoryId);
	Boolean existsByCategoryName (String name);
	List<Category> findByLevel (Integer level);
}
