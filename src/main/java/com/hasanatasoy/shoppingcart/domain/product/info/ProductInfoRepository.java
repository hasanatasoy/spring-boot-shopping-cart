package com.hasanatasoy.shoppingcart.domain.product.info;

import com.hasanatasoy.shoppingcart.domain.product.info.ProductInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInfoRepository extends CrudRepository<ProductInfo, Long> {
}
