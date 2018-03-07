package com.dj.app.service;

import com.dj.app.domain.Category;
import com.dj.app.domain.SubCategory;
import com.dj.app.dto.EquipmentsDto;
import com.dj.app.exception.CustomException;
import com.dj.app.repository.EquipmentRepository;
import static com.dj.app.transformers.CommonTransformer.*;
import com.dj.app.utils.DjConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EquipmentService {

	@Autowired
	EquipmentRepository equipmentRepository;

	public List<EquipmentsDto> fetchEquipmentsByCategory(Integer categoryId,Integer subCategoryId) {
		List<EquipmentsDto> equipments = new ArrayList<>(0);
		if(null != categoryId) {
			Set<Category> categories = new HashSet<>();
			categories.add(new Category(categoryId));
			equipments = equipmentRepository.findByCategories(categories)
					.map(toEquipmentDto).collect(Collectors.toList());
		}else if(null != subCategoryId) {
			Set<SubCategory> subCategories = new HashSet<>();
			subCategories.add(new SubCategory(subCategoryId));
			equipments = equipmentRepository.findBySubCategories(subCategories)
					.map(toEquipmentDto).collect(Collectors.toList());
		}else {
			throw new CustomException(DjConstants.REQUIRED_CATEGORY_OR_SUBCATEGORY);
		}
		return equipments;
	}
}
