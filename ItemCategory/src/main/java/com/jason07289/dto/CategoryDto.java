package com.jason07289.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jason07289.entity.Category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
	private Long categoryId;
	private String categoryName;
	private Long parentCategoryId;
	private Integer level;
	private Map<Long, CategoryDto> subCategory;// Map for JSON
	
	public CategoryDto(Category category) {
		this.categoryId = category.getCategoryId();
		this.categoryName = category.getCategoryName();
		this.level = category.getLevel();
		
		if(category.getParentCategory() == null) {
			this.parentCategoryId = null;
		} else {
			this.parentCategoryId = category.getParentCategory().getCategoryId();
		}
		
		if(category.getSubCategory() == null) {
			this.subCategory = null;			
		}else {
			this.subCategory = new HashMap<>();
			List<Category> categoryList = category.getSubCategory();
			for(int i=0; i<categoryList.size(); i++) {
				
				subCategory.put(categoryList.get(i).getCategoryId(), new CategoryDto(categoryList.get(i)));
			}
			
		}
	}

	@Override
	public String toString() {
		return "[categoryId=" + categoryId + ", categoryName=" + categoryName + ", parentCategoryId="
				+ parentCategoryId + ", level=" + level + ", subCategory=" + subCategory + "]";
	}
	
	
	
}
