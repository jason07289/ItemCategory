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
class ItemCategoryApplicationTests {
	
	@Autowired
	private CategoryService categoryService;
	
	@Order(1)
	@Test 
	void saveCategory() {
		
		System.out.println("##################################");
		System.out.println("############saveCategory 시작###########");
		CategoryDto categoryDto = new CategoryDto();// 부모
		categoryDto.setCategoryName("parents1");
		categoryService.saveCategory(categoryDto);
		
		categoryDto = new CategoryDto();// 부모2
		categoryDto.setCategoryName("parents2");
		categoryService.saveCategory(categoryDto);
		
		categoryDto = new CategoryDto();// 부모3
		categoryDto.setCategoryName("parents3");
		categoryService.saveCategory(categoryDto);
		
		categoryDto = new CategoryDto();// 부모4
		categoryDto.setCategoryName("parents4");
		categoryService.saveCategory(categoryDto);
		
		categoryDto = new CategoryDto();///자식1
		categoryDto.setCategoryName("testCate child1");
		categoryDto.setParentCategoryId((long) 1);
		categoryService.saveCategory(categoryDto);
		
		categoryDto = new CategoryDto();///자식2
		categoryDto.setCategoryName("testCate child2");
		//categoryDto.setParentCategoryName("testCa");//부모 카테고리 없는 경우
		categoryDto.setParentCategoryId((long) 1);
		categoryService.saveCategory(categoryDto);
		
		categoryDto = new CategoryDto();///자식3
		categoryDto.setCategoryName("testCate child3");
		categoryDto.setParentCategoryId((long) 3);
		categoryService.saveCategory(categoryDto);
		for(int i=4; i<20; i++) {
			categoryDto = new CategoryDto();///자식3
			categoryDto.setCategoryName("testCate child"+i);
			categoryDto.setParentCategoryId((long) 4);
			categoryService.saveCategory(categoryDto);
		}
		System.out.println("##################################");
		System.out.println("############saveCategory 종료###########");

	}
	
	@Order(2)
	@Test 
	void getCategoryByCategoryId() {
		
		System.out.println("##################################");
		System.out.println("############getCategoryByCategoryId 시작###########");
		System.out.println(categoryService.getCategoryByCategoryId((long) 1));
		System.out.println("############getCategoryByCategoryId 종료###########");
	}
	
	
	@Order(3)
	@Test
	void deleteCategory() {
		System.out.println("##################################");
		System.out.println("############deleteCategory 시작###########");
		categoryService.deleteCategory((long) 2);
		System.out.println("############deleteCategory 종료###########");
		
	}
	
	@Order(4)
	@Test
	void updateCategory() {
		System.out.println("##################################");
		System.out.println("############updateCategory 시작###########");
		CategoryDto categoryDto = new CategoryDto();// 수정할 category
		categoryDto.setCategoryName("Updated Parent1!");
		categoryService.updateCategory((long) 1, categoryDto);
		System.out.println("############updateCategory 종료###########");
		
	}
	
	
	@Order(5)
	@Test 
	void getAllCategory() {
		
		System.out.println("##################################");
		System.out.println("############getAllCategory 동작 확인###########");
		System.out.println(categoryService.getAllCategory());
	}
	
	
	//@Test 
	void contextLoads() {// 개발 중 테스트
		CategoryDto categoryDto = new CategoryDto();// 부모
		categoryDto.setCategoryName("testCate");
		categoryDto.setLevel(0);
		categoryService.saveCategory(categoryDto);
		
		categoryDto = new CategoryDto();// 부모2
		categoryDto.setCategoryName("testCate2");
		categoryService.saveCategory(categoryDto);
		
		categoryDto = new CategoryDto();// 부모3
		categoryDto.setCategoryName("testCate3");
		categoryService.saveCategory(categoryDto);
		
		categoryDto = new CategoryDto();///자식1
		categoryDto.setCategoryName("testCate child");
		categoryDto.setLevel(0);
		categoryDto.setParentCategoryId((long) 1);
		categoryService.saveCategory(categoryDto);
		
		categoryDto = new CategoryDto();///자식2
		categoryDto.setCategoryName("testCate child2!!");
		categoryDto.setLevel(0);
		//categoryDto.setParentCategoryName("testCa");//부모 카테고리 없는 경우
		categoryDto.setParentCategoryId((long) 1);
		categoryService.saveCategory(categoryDto);
		
		categoryDto = new CategoryDto();///자식3
		categoryDto.setCategoryName("testCate child");
		categoryDto.setLevel(0);
		categoryDto.setParentCategoryId((long) 3);
		categoryService.saveCategory(categoryDto);
		
		System.out.println("##################################");
		System.out.println("############save 동작 확인###########");
		System.out.println(categoryService.getCategoryByCategoryId((long) 1));
		
		
		
		categoryService.deleteCategory((long) 2);
		System.out.println("##################################");
		System.out.println("############delete 동작 확인###########");
		System.out.println(categoryService.getCategoryByCategoryId((long) 1));
		
		categoryDto = new CategoryDto();// 수정할 category
		categoryDto.setCategoryName("Updated Parent!");
		categoryService.updateCategory((long) 1, categoryDto);
		System.out.println("##################################");
		System.out.println("############update 동작 확인###########");
		System.out.println(categoryService.getCategoryByCategoryId((long) 1));
		
		
		System.out.println("##################################");
		System.out.println("############select 전체 개발###########");
		System.out.println(categoryService.getAllCategory());
		
	}

}
