package com.dj.app.dto;

import com.dj.app.utils.CategoryEnum;

public class CategoryFactory {
	
	//replace with enum
	public static CategoryDto getCategoryObject(CategoryEnum type) {
		switch (type) {
		case DJ:
			return new DJDto();
		case Band:
			return new BandDto();
		case Photographer:
			return new PhotgraphDto();
		default:
			break;
		}
		return null;
	}
}
