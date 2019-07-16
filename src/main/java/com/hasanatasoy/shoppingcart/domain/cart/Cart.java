package com.hasanatasoy.shoppingcart.domain.cart;

import com.hasanatasoy.shoppingcart.base.domain.BaseModel;
import com.hasanatasoy.shoppingcart.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@SequenceGenerator(name = "cartgenerator", sequenceName = "cart_seq")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart extends BaseModel {

    private String sessionId;
    @OneToMany
    private List<Product> products;
    private int totalCost;
    private int productCount;

    public int getTotalCost(){
        products.forEach((Product product)-> totalCost += product.getProductInfo().getCost());
        return totalCost;
    }

    public int getProductCount(){
        productCount = products.size();
        return productCount;
    }
}
