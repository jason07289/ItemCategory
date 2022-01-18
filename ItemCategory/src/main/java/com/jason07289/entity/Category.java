package com.jason07289.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="Category")
public class Category {//entity to DB table
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long categoryId;
	
	@Column(name = "categoryName")
	private String categoryName;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name ="parent_category_id")
	private Category parentCategory;
	
	@OneToMany (mappedBy = "parentCategory", cascade = CascadeType.ALL)
	private List<Category> subCategory = new ArrayList<>();
	
	private Integer level;
	
	

}
