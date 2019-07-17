package com.hasanatasoy.shoppingcart.converter.category;

import com.hasanatasoy.shoppingcart.converter.Converter;
import com.hasanatasoy.shoppingcart.domain.category.Category;
import com.hasanatasoy.shoppingcart.dto.category.CategoryDTO;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter implements Converter<Category, CategoryDTO> {
    @Override
    public CategoryDTO convert(Category category) {
        return new CategoryDTO(category.getId(), category.getCategoryName(), category.isMain(), category.getParents());
    }
}
