package com.example.demo.controller;


import com.example.demo.Mapper.ProductMapper;
import com.example.demo.Repository.CategoryRepository;
import com.example.demo.Repository.ProducerRepository;
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
@RequestMapping("/api/v1/")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProducerRepository producerRepository;

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


//    @RequestMapping(value = "products", method = RequestMethod.POST)
//    public ResponseEntity<Product> add(@Valid @RequestBody Product product) {
//        productRepository.save(product);
//        return new ResponseEntity<>(product, HttpStatus.OK);
//    }

    @RequestMapping(value = "products", method = RequestMethod.POST)
    @ResponseBody
    public void add(@RequestParam(value = "category") String category, @RequestParam(value = "producer") String producer,
                    @RequestParam(value = "name") String name, @RequestParam(value = "description") String description,
                    @RequestParam(value = "pieces") String pieces, @RequestParam(value = "price") String price,
                    @RequestParam(value = "promotion") boolean promotion) {


        Category cat = categoryRepository.findByTitle(category);
        Producer prod = producerRepository.findByTitle(producer);
        Product product = new Product();

        product.setName(name);
        product.setDescription(description);
        product.setCategory(cat);
        product.setPrice(new BigDecimal(price));
        product.setProducer(prod);
        product.setPieces(Integer.valueOf(pieces));
        product.setPromotion(promotion);

        productRepository.save(product);
     //   return "products";
    }


}
