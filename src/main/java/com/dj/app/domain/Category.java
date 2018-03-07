package com.dj.app.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.dj.app.utils.EnumUtils;

@Entity
@Table(name = "CATEGORY")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private Integer categoryId;
	
	private String categoryName;

	private String description;
	
	@Enumerated(EnumType.STRING)
	private EnumUtils.Status status;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	private Set<SubCategory> subCategory;
	
	public Category() {
		this.subCategory = new HashSet<>();
	}
	public Category(Integer categoryId) {
		this.categoryId =categoryId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public EnumUtils.Status getStatus() {
		return status;
	}

	public void setStatus(EnumUtils.Status status) {
		this.status = status;
	}

	public Set<SubCategory> getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(Set<SubCategory> subCategory) {
		this.subCategory = subCategory;
	}
	
}
