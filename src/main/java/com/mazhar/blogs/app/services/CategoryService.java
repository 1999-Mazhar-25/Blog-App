package com.mazhar.blogs.app.services;

import com.mazhar.blogs.app.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

    //create
    CategoryDto createCategory(CategoryDto categoryDto);


    //update
    CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

    //get by id
    CategoryDto getCategoryById(Integer categoryId);

    //get all
    List<CategoryDto> getAllCategory();


    //delete

    void deleteCategoryById(Integer categoryId);
}
