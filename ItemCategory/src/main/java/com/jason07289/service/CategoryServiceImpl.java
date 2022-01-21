package com.jason07289.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	
	@Override
	public Long saveCategory (CategoryDto categoryDto) {
		Category category = Category.builder()
				.categoryName(categoryDto.getCategoryName())
				.level(categoryDto.getLevel())
				.build();
		
		if(categoryDto.getParentCategoryId() == null || categoryDto.getParentCategoryId() == 0 ) {// root category
			 if(categoryRepository.existsByCategoryName(categoryDto.getCategoryName())) {
				 throw new CategoryException(Thread.currentThread().getStackTrace()[1].getMethodName() + ": 중복된 카테고리 이름 오류. ");
			 }
			 category.setLevel(1);
			 categoryRepository.save(category);
			
		} else {
			
			Category parentCategory = categoryRepository.findById(categoryDto.getParentCategoryId())
					.orElseThrow(() -> new CategoryException(Thread.currentThread().getStackTrace()[1].getMethodName() + ": 부모카테고리가 존재하지 않음."));
			category.setLevel(parentCategory.getLevel()+1);
			
			category.setParentCategory(parentCategory);
			parentCategory.getSubCategory().add(category);
		}
		
		return categoryRepository.save(category).getCategoryId();
	}
	
	@Override
	public Map<Long, CategoryDto> getCategoryByCategoryId(Long categoryId) {
		
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(()-> new CategoryException(Thread.currentThread().getStackTrace()[1].getMethodName() + ": 해당 카테고리는 존재하지 않음."));
		
		CategoryDto categoryDto = new CategoryDto(category);
		
		Map <Long, CategoryDto> categoryMap = new HashMap<>();
		categoryMap.put(categoryDto.getCategoryId(), categoryDto);
		
		return categoryMap;
	}

	@Override
	public void deleteCategory(Long categoryId) {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new CategoryException(Thread.currentThread().getStackTrace()[1].getMethodName() + ": 해당 카테고리는 존재하지 않음."));
		
		if(category.getSubCategory().size() == 0) {		
			categoryRepository.deleteById(category.getCategoryId());
			
		} else {
			throw new CategoryException(Thread.currentThread().getStackTrace()[1].getMethodName() + ": 하위카테고리가 존재하여 삭제불가.");
		}
		
	}

	@Override
	public Long updateCategory(Long categoryId, CategoryDto categoryDto) {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new CategoryException(Thread.currentThread().getStackTrace()[1].getMethodName() + ": 해당 카테고리는 존재하지 않음."));
		
        category.setCategoryName(categoryDto.getCategoryName());
        
		return categoryRepository.save(category).getCategoryId();
		
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		
		List<Category> categoryList = categoryRepository.findByLevel(1);
		List<CategoryDto> CategoryDtoList = new ArrayList<CategoryDto>();
		for(Category c : categoryList) {
			CategoryDtoList.add(new CategoryDto(c));
		}
		
		return CategoryDtoList;
	}
	
	
	
}
