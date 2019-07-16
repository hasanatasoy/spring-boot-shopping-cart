package com.hasanatasoy.shoppingcart.domain.product.images;


import com.hasanatasoy.shoppingcart.base.domain.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "product_image")
@SequenceGenerator(name = "productimagegenerator", sequenceName = "product_image_seq")
@Getter
@Setter
public class ProductImage extends BaseModel {

    @NotNull
    private String imageUrl = "This part empty for now";
    @NotNull
    private String colorName;
}
