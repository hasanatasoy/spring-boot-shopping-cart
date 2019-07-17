package com.hasanatasoy.shoppingcart.controller;

import com.hasanatasoy.shoppingcart.base.messages.Response;
import com.hasanatasoy.shoppingcart.dto.category.CategoryDTO;
import com.hasanatasoy.shoppingcart.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //@PreAuthorize(value = "hasRole('Admin')") FOR NOW CLOSED
    @RequestMapping(value = "/create/main/{categoryName}", method = RequestMethod.GET)
    public Response<CategoryDTO> createMainCategory(@PathVariable String categoryName){
        categoryService.validateDoesNotExistWith(categoryName);
        categoryService.createMainCategory(categoryName);
        CategoryDTO categoryDTO = categoryService.getDtoWith(categoryName);
        return Response.<CategoryDTO>builder().result(categoryDTO).message("Main category successfully created.").build();
    }

    //@PreAuthorize(value = "hasRole('Admin')") FOR NOW CLOSED
    @RequestMapping(value = "/create/sub/{categoryName}/{parentName}", method = RequestMethod.GET)
    public Response<CategoryDTO> createSubCategory(@PathVariable String categoryName, @PathVariable String parentName){
        categoryService.validateDoesNotExistWith(categoryName);
        categoryService.createWithParent(categoryName, parentName);
        CategoryDTO categoryDTO = categoryService.getDtoWith(categoryName);
        return Response.<CategoryDTO>builder().result(categoryDTO).message("Sub category successfully created.").build();
    }
}
