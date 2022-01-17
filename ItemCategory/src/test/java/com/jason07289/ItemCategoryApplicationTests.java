package com.jason07289;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jason07289.repository.CategoryRepository;

@SpringBootTest
class ItemCategoryApplicationTests {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Test
	void contextLoads() {
		System.out.println("test:  "+  categoryRepository.findAll());
	}

}
