package com.example.demo.Mapper;

import com.example.demo.dtos.CategoryDto;
import com.example.demo.entities.Category;
import com.example.demo.entities.Producer;
import com.example.demo.entities.Product;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CategoryMapper implements Mapper<Category, CategoryDto>{


    @Override
    public CategoryDto map(Category from) {

//        List<String> producers = from.getProducers()
//                .stream()
//                .map(ProducersToString.INSTANCE)
//                .collect(Collectors.toList());

        return new CategoryDto(from.getName(), from.getDescription());
    }

    private enum ProducersToString implements Function<Producer, String> {
        INSTANCE;

        @Override
        public String apply(Producer producer) {
            return producer.getName();
        }
    }

}
