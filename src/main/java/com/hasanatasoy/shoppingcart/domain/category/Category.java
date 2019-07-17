package com.hasanatasoy.shoppingcart.domain.category;

import com.hasanatasoy.shoppingcart.base.domain.BaseModel;
import com.hasanatasoy.shoppingcart.exceptionhandler.base.exception.ForbiddenException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@SequenceGenerator(name = "categorygenerator", sequenceName = "category_seq")
@Getter
@Setter
@NoArgsConstructor
public class Category extends BaseModel {

    @NotNull
    private String categoryName;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Category> parents = new ArrayList<>();
    @NotNull
    private boolean isMain = false;

    public Category(String categoryName, Category parentCategory){
        this.categoryName = categoryName;
        parents.add(parentCategory);
    }

    public Category(String mainCategoryName){
        this.categoryName = mainCategoryName;
        isMain = true;
    }
}
