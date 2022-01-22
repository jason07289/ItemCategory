package com.jason07289;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jason07289.dto.CategoryDto;
import com.jason07289.service.CategoryService;

@SpringBootTest
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class ItemCategoryQueryTest {
	
	@Autowired
	private CategoryService categoryService;
	
	
	
	@Order(1)
	@Test 
	void saveCategoryCase1() {
		
		System.out.println("********************************************************************************");
		System.out.println("############saveCategory 시작###########");
		CategoryDto categoryDto = new CategoryDto();// 부모
		categoryDto.setCategoryName("parents1");
		categoryService.saveCategory(categoryDto);
		
		categoryDto = new CategoryDto();///자식1
		categoryDto.setCategoryName("testCate child1");
		categoryDto.setParentCategoryId((long) 1);
		categoryService.saveCategory(categoryDto);
		System.out.println("##################################");
		System.out.println("############saveCategory 종료###########");

	}
	
	@Order(2)
	@Test 
	void getAllCategory() {
		
		System.out.println("********************************************************************************");
 		System.out.println("############getAllCategory 시작###########");
		System.out.println(categoryService.getAllCategory());
		System.out.println("############getAllCategory 종료###########");
	}
}
