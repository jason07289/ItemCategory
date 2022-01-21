package com.jason07289.service;

import java.util.List;
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
	public Map<Long, CategoryDto> getCategoryByCategoryId(Long categoryId);
	
	/**
	 * 전체 카테고리 리턴 (최상위 카테고리를 리스트에 담아 리턴한다.)
	 * 
	 * @return 
	 */
	public List<CategoryDto> getAllCategory();
	
	/**
	 * 카테고리 삭제 (하위 카테고리를 포함할 경우 삭제 불가)
	 * @param categoryId
	 */
	public void deleteCategory(Long categoryId);
	
	/**
	 * 카테고리 업데이트 
	 * @param categoryDto
	 * @return id
	 */
	public Long updateCategory(Long categoryId, CategoryDto categoryDto);
	
	
}
