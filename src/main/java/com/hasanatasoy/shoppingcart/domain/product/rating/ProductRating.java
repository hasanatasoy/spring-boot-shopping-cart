package com.hasanatasoy.shoppingcart.domain.product.rating;

import com.hasanatasoy.shoppingcart.domain.product.rating.comment.ProductComment;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity(name = "ProductRating")
@Getter
@Setter
public class ProductRating {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @OneToMany
    private List<ProductComment> productComments;
    @NotNull
    private int totalVote;
}
