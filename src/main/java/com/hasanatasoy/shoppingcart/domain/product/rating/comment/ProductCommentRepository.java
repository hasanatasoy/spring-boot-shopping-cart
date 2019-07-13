package com.hasanatasoy.shoppingcart.domain.product.rating.comment;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCommentRepository extends CrudRepository<ProductComment, Long> {
}
