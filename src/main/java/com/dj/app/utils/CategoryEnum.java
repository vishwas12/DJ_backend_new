package com.dj.app.utils;

public enum CategoryEnum {
	DJ(1,"DJ"),
	Photographer(2,"Photographer"),
	Band(3, "Band");
	
	private int categoryId;
	private String name;
	
	private CategoryEnum(int categoryId, String name){
		this.categoryId = categoryId;
		this.name = name;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
