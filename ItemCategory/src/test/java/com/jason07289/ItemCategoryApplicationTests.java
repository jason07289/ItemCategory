package com.jason07289;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jason07289.dto.CategoryDto;
import com.jason07289.entity.Category;
import com.jason07289.repository.CategoryRepository;
import com.jason07289.service.CategoryService;

@SpringBootTest
class ItemCategoryApplicationTests {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Test
	void contextLoads() {
		CategoryDto categoryDto = new CategoryDto();// 부모
		categoryDto.setCategoryName("testCate");
		categoryDto.setLevel(0);
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
		
	}

}
