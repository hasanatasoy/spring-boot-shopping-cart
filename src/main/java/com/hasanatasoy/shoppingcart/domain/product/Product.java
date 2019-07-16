package com.hasanatasoy.shoppingcart.domain.product;

import com.hasanatasoy.shoppingcart.base.domain.BaseModel;
import com.hasanatasoy.shoppingcart.domain.category.Category;
import com.hasanatasoy.shoppingcart.domain.product.images.ProductImage;
import com.hasanatasoy.shoppingcart.domain.product.info.ProductInfo;
import com.hasanatasoy.shoppingcart.domain.product.rating.ProductRating;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@SequenceGenerator(name = "productgenerator", sequenceName = "product_seq")
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
    private List<ProductImage> productImages;
    @OneToOne(fetch = FetchType.LAZY)
    @NotNull
    private ProductInfo productInfo;
    @OneToOne(fetch = FetchType.LAZY)
    private Category category;


}
