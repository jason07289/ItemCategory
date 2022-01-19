package com.jason07289.service;

import java.util.HashMap;
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
		
		if(categoryDto.getParentCategoryName() == null) {// root category
			 if(categoryRepository.existsByCategoryName(categoryDto.getCategoryName())) {
				 throw new CategoryException(Thread.currentThread().getStackTrace()[1].getMethodName() + ": 중복된 카테고리 이름 오류. ");
			 }
			 category.setLevel(1);
			 categoryRepository.save(category);
			
		} else {
			
			Category parentCategory = categoryRepository.findByCategoryName(categoryDto.getParentCategoryName())
					.orElseThrow(() -> new CategoryException(Thread.currentThread().getStackTrace()[1].getMethodName() + ": 부모카테고리가 존재하지 않음."));
			category.setLevel(parentCategory.getLevel()+1);
			
			category.setParentCategory(parentCategory);
			parentCategory.getSubCategory().add(category);
		}
		
		return categoryRepository.save(category).getCategoryId();
	}
	
	@Override
	public Map<String, CategoryDto> getCategoryByCategoryName(String name) {
		
		Category category = categoryRepository.findByCategoryName(name)
				.orElseThrow(()-> new CategoryException(Thread.currentThread().getStackTrace()[1].getMethodName() + ": 해당 카테고리는 존재하지 않음."));
		
		CategoryDto categoryDto = new CategoryDto(category);
		
		Map <String, CategoryDto> categoryMap = new HashMap<>();
		categoryMap.put(categoryDto.getCategoryName(), categoryDto);
		
		return categoryMap;
	}

	@Override
	public void deleteCategory(Long categoryId) {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new CategoryException(Thread.currentThread().getStackTrace()[1].getMethodName() + ": 해당 카테고리는 존재하지 않음."));
		
		if(category.getSubCategory().size() == 0) {
			
			//부모의 참조관계부터 끊어준다.
			CategoryDto CategoryDto = new CategoryDto(category);
			if(!"Root".equals(CategoryDto.getParentCategoryName())) {//현재 카테고리내 부모의 이름이 Root라면 최상위, 최상위 카테고리가 아닐경우
				Category parentCategory = categoryRepository.findById(category.getParentCategory().getCategoryId())
						.orElseThrow(() -> new CategoryException(Thread.currentThread().getStackTrace()[1].getMethodName() + ": 부모카테고리가 존재하지 않음."));
				parentCategory.getSubCategory().remove(category);//부모카테고리의 리스트상에서 카테고리를 지워버린다.
			}
			categoryRepository.deleteById(category.getCategoryId());
			
		} else {
			throw new CategoryException(Thread.currentThread().getStackTrace()[1].getMethodName() + ": 하위카테고리가 존재하여 삭제 불가.");
		}
		
	}
	
	
	
}
