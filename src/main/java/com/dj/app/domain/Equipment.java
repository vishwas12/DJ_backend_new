package com.dj.app.domain;


import com.dj.app.utils.EnumUtils;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Equipment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "equipment_id")
	private Integer equipmentId;

	private String equipmentName;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "equipment_category", joinColumns = {
			@JoinColumn(name = "equipment_id", referencedColumnName = "equipment_id") }, inverseJoinColumns = {
			@JoinColumn(name = "category_id", referencedColumnName = "category_id") })
	private Set<Category> categories;


	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "equipment_sub_category", joinColumns = {
			@JoinColumn(name = "equipment_id", referencedColumnName = "equipment_id") }, inverseJoinColumns = {
			@JoinColumn(name = "sub_category_id", referencedColumnName = "sub_category_id") })
	private Set<SubCategory> subCategories;

	@Enumerated(EnumType.STRING)
	private EnumUtils.Status status;

	public Integer getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public Set<SubCategory> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(Set<SubCategory> subCategories) {
		this.subCategories = subCategories;
	}

	public EnumUtils.Status getStatus() {
		return status;
	}

	public void setStatus(EnumUtils.Status status) {
		this.status = status;
	}
}
