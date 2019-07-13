package com.hasanatasoy.shoppingcart.domain.cart;

import com.hasanatasoy.shoppingcart.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    private String sessionId;
    private List<Product> products;
    private int totalCost;
    private int productCount;

    public int getTotalCost(){
        products.forEach((Product product)->{
            totalCost += product.getProductInfo().getCost();
        });
        return totalCost;
    }

    public int getProductCount(){
        productCount = products.size();
        return productCount;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
