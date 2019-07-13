package com.hasanatasoy.shoppingcart.domain.product.color;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity(name = "ProductColor")
@Getter
@Setter
public class ProductColor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotNull
    private String imageUrl = "This part empty for now";
    @NotNull
    private String colorName;
}
