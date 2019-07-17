package com.hasanatasoy.shoppingcart.service;

import com.hasanatasoy.shoppingcart.converter.category.CategoryConverter;
import com.hasanatasoy.shoppingcart.domain.category.Category;
import com.hasanatasoy.shoppingcart.domain.category.CategoryRepository;
import com.hasanatasoy.shoppingcart.domain.user.authinfo.UserAuth;
import com.hasanatasoy.shoppingcart.dto.category.CategoryDTO;
import com.hasanatasoy.shoppingcart.exceptionhandler.base.exception.BadRequestExcepiton;
import com.hasanatasoy.shoppingcart.exceptionhandler.category.CategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryConverter categoryConverter;

    public Category findBy(String categoryName){
        Optional<Category> category = categoryRepository.findByCategoryName(categoryName);
        boolean categoryDoesNotExist = !category.isPresent();
        if(categoryDoesNotExist)
            throw new CategoryNotFoundException(categoryName);
        return category.get();
    }

    public void validateDoesNotExistWith(String categoryName) {
        boolean doesExistCategory = categoryRepository.findByCategoryName(categoryName).isPresent();
        if(doesExistCategory)
            throw new BadRequestExcepiton("Already have category with name: "+categoryName);
    }

    public void validateDoesExistWith(String categoryName) {
        findBy(categoryName);
    }

    public void createMainCategory(String categoryName){
        Category category = new Category(categoryName);
        categoryRepository.save(category);
    }

    public void createWithParent(String categoryName, String parentName) {
        Category parentCategory = findBy(parentName);
        Category category = new Category(categoryName, parentCategory);
        categoryRepository.save(category);
    }

    public CategoryDTO getDtoWith(String categoryName) {
        Category category = findBy(categoryName);
        return categoryConverter.convert(category);
    }
}
