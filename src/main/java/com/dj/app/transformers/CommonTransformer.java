package com.dj.app.transformers;

import com.dj.app.domain.Category;
import com.dj.app.domain.Equipment;
import com.dj.app.dto.CategoryDto;
import com.dj.app.dto.EquipmentsDto;

import java.util.function.Function;

public class CommonTransformer {

	public static Function<Equipment,EquipmentsDto> toEquipmentDto =
			(equipment -> new EquipmentsDto(equipment.getEquipmentId(),equipment.getEquipmentName()));

	public static Function<Category,CategoryDto> toCategoryDto =
			(category -> new CategoryDto(category.getCategoryId(),category.getCategoryName(),category.getDescription()));
}
