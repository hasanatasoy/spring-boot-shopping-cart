package com.hasanatasoy.shoppingcart.domain.product.rating.comment;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity(name = "ProductComment")
public class ProductComment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
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

    @PrePersist
    public void onCreate(){
        createdAt = new Date();
    }

    @PostPersist
    public void onUpdate(){
        updatedAt = new Date();
    }
}
