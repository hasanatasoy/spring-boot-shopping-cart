package com.hasanatasoy.shoppingcart.domain.category;

import com.hasanatasoy.shoppingcart.domain.category.subcategory.SubCategory;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotNull
    private CategoryName categoryName;
    @OneToMany(fetch = FetchType.LAZY)
    private List<SubCategory> subCategories;
}
