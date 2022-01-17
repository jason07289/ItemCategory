package com.jason07289.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long categoryId;
	
	@Column(name = "categoryName")
	private long categoryName;
	
	@Column(name = "categoryChildId")
	private long categoryChildId;

}
