package com.hasanatasoy.shoppingcart.domain.product.info;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "ProductInformation")
@Getter
@Setter
public class ProductInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @Size
    private int cost;
    @Size
    private int count;
}
