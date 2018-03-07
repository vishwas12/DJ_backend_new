package com.dj.app.repository;

import com.dj.app.domain.Category;
import com.dj.app.domain.Equipment;
import com.dj.app.domain.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment,Integer>{

	Stream<Equipment> findByCategories(Set<Category> categories);

	Stream<Equipment> findBySubCategories(Set<SubCategory> subCategories);

}
