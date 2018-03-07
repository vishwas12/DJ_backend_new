package com.dj.app.repository;

import com.dj.app.domain.Category;
import com.dj.app.utils.EnumUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.stream.Stream;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

	Category findByCategoryId(Integer categoryId);

	Stream<Category> findAllByStatus(EnumUtils.Status status, Sort sort);
}
