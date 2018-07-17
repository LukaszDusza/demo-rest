package com.example.demo.Mapper;

import com.example.demo.dtos.ProductDto;
import com.example.demo.entities.Category;
import com.example.demo.entities.Product;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ProductMapper implements Mapper<Product, ProductDto> {

    @Override
    public ProductDto map(Product from) {

        String categories = from.getCategories()
                .stream()
                .map(CategoriesToString.INSTANCE)
                .collect(Collectors.joining(" ,"));

        return new ProductDto (

                from.getName(),
                from.getDescription(),
                categories, from.getPrice(),
                from.getProducer().getName(),
                from.getPieces(),
                from.isPromotion()
        );
    }


    private enum CategoriesToString implements Function<Category, String> {
        INSTANCE;

        @Override
        public String apply(Category category) {
            return category.getName();
        }
    }


}
