package com.hasanatasoy.shoppingcart.domain.category.subcategory;

import com.hasanatasoy.shoppingcart.domain.category.subcategory.SubCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepository extends CrudRepository<SubCategory, Long> {
}
