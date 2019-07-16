package com.hasanatasoy.shoppingcart.domain.product.info;

import com.hasanatasoy.shoppingcart.base.domain.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "product_info")
@SequenceGenerator(name = "productinfogenerator", sequenceName = "product_info_seq")
@Getter
@Setter
public class ProductInfo extends BaseModel {

    @NotNull
    private String name;
    @NotNull
    private String description;
    @Size
    private int cost;
    @Size
    private int count;
}
