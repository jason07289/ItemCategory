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
	private String parentCategoryName;
	private Integer level;
	private Map<String, CategoryDto> subCategory;// Map to JSON
	
	public CategoryDto(Category category) {
		this.categoryId = category.getCategoryId();
		this.categoryName = category.getCategoryName();
		this.level = category.getLevel();
		
		if(category.getParentCategory() == null) {
			this.parentCategoryName = "Root";
		} else {
			this.parentCategoryName = category.getParentCategory().getCategoryName();
		}
		
		if(category.getSubCategory() == null) {
			this.subCategory = null;			
		}else {
			this.subCategory = new HashMap<>();
			List<Category> categoryList = category.getSubCategory();
			for(int i=0; i<categoryList.size(); i++) {
				
				subCategory.put(categoryList.get(i).getCategoryName(), new CategoryDto(categoryList.get(i)));
			}
			
		}
	}

	@Override
	public String toString() {
		return "CategoryDto [categoryId=" + categoryId + ", categoryName=" + categoryName + ", parentCategoryName="
				+ parentCategoryName + ", level=" + level + ", subCategory=" + subCategory + "]";
	}
	
	
	
}
