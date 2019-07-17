package com.hasanatasoy.shoppingcart.dto.category;

import com.hasanatasoy.shoppingcart.domain.category.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private Long id;
    private String categoryName;
    private boolean isMain;
    private List<Category> parents;
}
