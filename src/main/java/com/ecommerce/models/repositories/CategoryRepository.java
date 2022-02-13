package com.ecommerce.models.repositories;

import com.ecommerce.models.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
