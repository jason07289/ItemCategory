package com.jason07289.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.jason07289.dto.CategoryDto;
import com.jason07289.entity.Category;
import com.jason07289.exception.CategoryException;
import com.jason07289.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {
	private final CategoryRepository categoryRepository;
	
	/**
	 * 
	 * 카테고리 저장
	 * @param categoryDto
	 * @return id
	 */
	public Long saveCategory (CategoryDto categoryDto) {
		Category category = Category.builder()
				.categoryName(categoryDto.getCategoryName())
				.level(categoryDto.getLevel())
				.build();
		
		if(categoryDto.getParentCategoryName() == null) {// root category
			 if(categoryRepository.existsByCategoryName(categoryDto.getCategoryName())) {
				 throw new CategoryException("중복된 카테고리 이름 오류. ");
			 }
			 category.setLevel(0);
			 categoryRepository.save(category);
			
		} else {
			
			Category parentCategory = categoryRepository.findByCategoryName(categoryDto.getParentCategoryName())
					.orElseThrow(() -> new CategoryException("부모카테고리가 존재하지 않습니다."));
			category.setLevel(parentCategory.getLevel()+1);
			
			category.setParentCategory(parentCategory);
			parentCategory.getSubCategory().add(category);
		}
		
		return categoryRepository.save(category).getCategoryId();
	}
	
}
