package com.example.demo.controller;


import com.example.demo.Mapper.CategoryMapper;
import com.example.demo.Repository.CategoryRepository;
import com.example.demo.dtos.CategoryDto;
import com.example.demo.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/api/")
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("categories")
    public List<CategoryDto> categories() {
        List<Category> categories = categoryRepository.findAll();

        CategoryMapper categoryMapper = new CategoryMapper();
        List<CategoryDto> categoryDtos = new ArrayList<>();
        for(Category c : categories) {
            CategoryDto categoryDto = categoryMapper.map(c);
            categoryDtos.add(categoryDto);
        }
        return categoryDtos;
    }

}
