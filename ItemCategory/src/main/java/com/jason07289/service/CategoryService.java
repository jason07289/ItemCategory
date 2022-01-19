package com.jason07289.service;

import java.util.Map;

import com.jason07289.dto.CategoryDto;

public interface CategoryService {
	
	/**
	 * 
	 * 카테고리 저장
	 * @param categoryDto
	 * @return id
	 */
	public Long saveCategory (CategoryDto categoryDto);
	
	/**
	 * 이름을 통해 카테고리(하위 카테고리를 모두 포함한) 리턴
	 * @param name
	 * @return categoryMap
	 */
	public Map<String, CategoryDto> getCategoryByCategoryName(String name);
	
	/**
	 * 카테고리 삭제 (하위 카테고리를 포함할 경우 삭제 불가)
	 * @param categoryId
	 */
	public void deleteCategory(Long categoryId);
	
	
}
