package com.example.demo.controller;


import com.example.demo.Repository.ProductRepository;
import com.example.demo.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("products")
    public List<Product> products() {
        return productRepository.findAll();
    }

    @RequestMapping(value = "products", method = RequestMethod.POST)
    public ResponseEntity<Product> addBook(@Valid @RequestBody Product product) {
        productRepository.save(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }


}
