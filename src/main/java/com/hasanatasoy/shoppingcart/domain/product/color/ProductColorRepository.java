package com.hasanatasoy.shoppingcart.domain.product.color;

import com.hasanatasoy.shoppingcart.domain.product.color.ProductColor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductColorRepository extends CrudRepository<ProductColor, Long> {
}
