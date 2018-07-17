package com.example.demo.Mapper;

import com.example.demo.dtos.ProducerDto;
import com.example.demo.entities.Producer;
import com.example.demo.entities.Product;

import java.util.function.Function;
import java.util.stream.Collectors;

public class ProducerMapper implements Mapper<Producer, ProducerDto>{
    @Override
    public ProducerDto map(Producer from) {

        String products = from.getProduct()
                .stream()
                .map(ProductsToString.INSTANCE)
                .collect(Collectors.joining(" ,"));

        return new ProducerDto(from.getName(), from.getDescription(), products);
    }

    private enum ProductsToString implements Function<Product, String> {
        INSTANCE;

        @Override
        public String apply(Product product) {
            return product.getName();
        }
    }
}
