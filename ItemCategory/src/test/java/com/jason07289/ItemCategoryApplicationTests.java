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
		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setCategoryName("testCate");
		categoryDto.setLevel(0);
		categoryService.saveCategory(categoryDto);
		
		System.out.println("##################################");
		System.out.println("############save 동작 확인###########");
		System.out.println(categoryRepository.findAll());
	}

}
