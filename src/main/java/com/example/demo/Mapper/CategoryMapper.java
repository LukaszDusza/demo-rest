package com.example.demo.Mapper;

import com.example.demo.dtos.CategoryDto;
import com.example.demo.entities.Category;
import com.example.demo.entities.Product;

import java.util.function.Function;
import java.util.stream.Collectors;

public class CategoryMapper implements Mapper<Category, CategoryDto>{


    @Override
    public CategoryDto map(Category from) {

        String products = from.getProducts()
                .stream()
                .map(ProductToString.INSTANCE)
                .collect(Collectors.joining(" ,"));

        return new CategoryDto(from.getName(), from.getDescription(), products);
    }

    private enum ProductToString implements Function<Product, String> {
        INSTANCE;

        @Override
        public String apply(Product product) {
            return product.getName();
        }
    }

}
