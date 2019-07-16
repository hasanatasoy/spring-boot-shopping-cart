package com.hasanatasoy.shoppingcart.domain.product.rating.comment;

import com.hasanatasoy.shoppingcart.base.domain.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "product_comment")
@SequenceGenerator(name = "productcommentgenerator", sequenceName = "product_comment_seq")
@Getter
@Setter
public class ProductComment extends BaseModel {

    @NotNull
    private String commentator;
    @NotNull
    private String comment;
    @NotNull
    @Size(min = 1, max = 5)
    private byte vote;
    @NotNull
    private boolean haveProduct;
    @Temporal(TemporalType.DATE)
    private Date createdAt;
    @Temporal(TemporalType.DATE)
    private Date updatedAt;
    private int commentLike = 0;
}
