package com.mazhar.blogs.app.services.impl;

import com.mazhar.blogs.app.entities.Category;
import com.mazhar.blogs.app.exceptions.ResourceNotFoundException;
import com.mazhar.blogs.app.payloads.CategoryDto;
import com.mazhar.blogs.app.repositories.CategoryRepo;
import com.mazhar.blogs.app.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = this.modelMapper.map(categoryDto, Category.class);
        Category addedCategory = this.categoryRepo.save(category);
        return this.modelMapper.map(addedCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException
                        ("category","CategoryId",categoryId));
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        Category updatedCategory = this.categoryRepo.save(category);
        return this.modelMapper.map(updatedCategory, CategoryDto.class);


    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {

      Category category =  this.categoryRepo.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException
                        ("Category","CategoryId",categoryId));

        return this.modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory() {

        List<Category> categories = this.categoryRepo.findAll();
       List<CategoryDto> categoryDtoList = categories.stream()
                .map(category ->this.modelMapper.map(category,CategoryDto.class))
                .collect(Collectors.toList());

        return categoryDtoList;
    }

    @Override
    public void deleteCategoryById(Integer categoryId) {
        Category category =  this.categoryRepo.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId",categoryId));
        this.categoryRepo.delete(category);
    }


}
