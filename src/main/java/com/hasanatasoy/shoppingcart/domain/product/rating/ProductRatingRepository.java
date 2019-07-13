package com.hasanatasoy.shoppingcart.domain.product.rating;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRatingRepository extends CrudRepository<ProductRating, Long> {
}
