package com.dj.app.service;

import com.dj.app.dto.CategoryDto;
import com.dj.app.repository.CategoryRepository;
import com.dj.app.transformers.CommonTransformer;
import com.dj.app.utils.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	public List<CategoryDto> fetchCategoryList() {
		Sort sort = new Sort(Sort.Direction.ASC,"categoryName");
		List<CategoryDto>  categories = categoryRepository.findAllByStatus(EnumUtils.Status.ACTIVE,sort)
				.map(CommonTransformer.toCategoryDto).collect(Collectors.toList());
		return categories;
	}
}
