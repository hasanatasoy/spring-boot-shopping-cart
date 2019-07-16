package com.hasanatasoy.shoppingcart.domain.product.rating;

import com.hasanatasoy.shoppingcart.base.domain.BaseModel;
import com.hasanatasoy.shoppingcart.domain.product.rating.comment.ProductComment;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "product_rating")
@SequenceGenerator(name = "productratinggenerator", sequenceName = "product_rating_seq")
@Getter
@Setter
public class ProductRating extends BaseModel {

    @OneToMany
    private List<ProductComment> productComments;
    @NotNull
    private int totalVote;
}
