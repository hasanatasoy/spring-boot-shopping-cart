package com.hasanatasoy.shoppingcart.domain.product.info;

import com.hasanatasoy.shoppingcart.domain.product.info.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInfoRepository extends JpaRepository<ProductInfo, Long> {
}
