package com.dj.app.dto;

public class CategoryFactory {
	
	//replace with enum
	public static CategoryDto getCategoryObject(String type) {
		if("Photograp".equals(type)){
			return new PhotgraphDto();
		}else if("Band".equals(type)){
			return new BandDto();
		}
		return null;
	}
}
