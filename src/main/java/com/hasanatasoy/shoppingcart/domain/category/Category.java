package com.hasanatasoy.shoppingcart.domain.category;

import com.hasanatasoy.shoppingcart.base.domain.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@SequenceGenerator(name = "categorygenerator", sequenceName = "category_seq")
@Getter
@Setter
public class Category extends BaseModel {

    @NotNull
    private CategoryName categoryName;
    @OneToOne(fetch = FetchType.LAZY)
    private Category parent;
}
