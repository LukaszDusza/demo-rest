package com.example.demo.controller;


import com.example.demo.Mapper.ProductMapper;
import com.example.demo.Repository.ProductRepository;
import com.example.demo.dtos.ProductDto;
import com.example.demo.entities.Category;
import com.example.demo.entities.Producer;
import com.example.demo.entities.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/api/")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

//    @GetMapping("products")
//    public List<Product> products() {
//        return productRepository.findAll();
//    }

//    @GetMapping("products")
//    public List<ProductDto> products() {
//        List<Product> products = productRepository.findAll();
//        ModelMapper modelMapper = new ModelMapper();
//        List<ProductDto> productDtos = new ArrayList<>();
//        for(Product p : products) {
//            ProductDto productDto = new ProductDto();
//            modelMapper.map(p, productDto);
//            productDtos.add(productDto);
//        }
//        return productDtos;
//    }

    @GetMapping("products")
    public List<ProductDto> products() {
        List<Product> products = productRepository.findAll();
        ProductMapper productMapper = new ProductMapper();
        List<ProductDto> productDtos = new ArrayList<>();
        for(Product p : products) {
            ProductDto productDto = productMapper.map(p);
            productDtos.add(productDto);
        }
        return productDtos;
    }


    @RequestMapping(value = "products", method = RequestMethod.POST)
    public ResponseEntity<Product> add(@Valid @RequestBody Product product) {
        productRepository.save(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }



}
