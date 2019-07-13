package com.hasanatasoy.shoppingcart.domain.product;

import com.hasanatasoy.shoppingcart.base.domain.BaseModel;
import com.hasanatasoy.shoppingcart.domain.category.subcategory.SubCategory;
import com.hasanatasoy.shoppingcart.domain.product.color.ProductColor;
import com.hasanatasoy.shoppingcart.domain.product.info.ProductInfo;
import com.hasanatasoy.shoppingcart.domain.product.rating.ProductRating;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
public class Product extends BaseModel {

    @NotNull
    @ElementCollection
    @CollectionTable(name = "ProductImagesUrl", joinColumns = @JoinColumn(name = "Product_id"))
    private List<String> productImagesUrl;
    @OneToOne(fetch = FetchType.LAZY)
    private ProductRating productRating;
    @OneToMany(fetch = FetchType.LAZY)
    @NotNull
    private List<ProductColor> productColors;
    @OneToOne(fetch = FetchType.LAZY)
    @NotNull
    private ProductInfo productInfo;
    @OneToMany(fetch = FetchType.LAZY)
    private List<SubCategory> categories;


}
