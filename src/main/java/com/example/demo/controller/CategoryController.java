package com.example.demo.controller;


import com.example.demo.Mapper.CategoryMapper;
import com.example.demo.Repository.CategoryRepository;
import com.example.demo.dtos.CategoryDto;
import com.example.demo.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@CrossOrigin
@RestController
@RequestMapping("/api/")
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("categories")
    public List<CategoryDto> categories(@RequestParam(value = "expression", required = false) String expression) {
        List<Category> categories;

        if(expression == null || expression.equalsIgnoreCase("")) {
            categories = categoryRepository.findAll();
        } else {
            categories = categoryRepository.searchByAnything(expression);
        }

        CategoryMapper categoryMapper = new CategoryMapper();
        List<CategoryDto> categoryDtos = new ArrayList<>();
        for (Category c : categories) {
            CategoryDto categoryDto = categoryMapper.map(c);
            categoryDtos.add(categoryDto);
        }
        return categoryDtos;
    }

    @RequestMapping(value = "categories", method = RequestMethod.POST)
    public ResponseEntity add (
            @RequestParam(value = "title") String title,
            @RequestParam(value = "description") String description)
             {

        Optional<Category> categoryOptional = categoryRepository.findOneByTitle(title);

        if(categoryOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        } else {
            Category category = new Category();
            category.setName(title);
            category.setDescription(description);
            categoryRepository.save(category);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
    }



//zastąpiona warunkiem if w metodzie powyzej.

//    @RequestMapping(value = "categories/search", method = RequestMethod.GET)
//    public List<CategoryDto> categoriesByTitle(@RequestParam(value = "title", required = false) String title) {
//
//        List<Category> categories = categoryRepository.searchByAnything(title);
//
//        CategoryMapper categoryMapper = new CategoryMapper();
//        List<CategoryDto> categoryDtos = new ArrayList<>();
//        for (Category c : categories) {
//            CategoryDto categoryDto = categoryMapper.map(c);
//            categoryDtos.add(categoryDto);
//        }
//        return categoryDtos;
//
//    }


}
