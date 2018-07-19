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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.*;

@CrossOrigin
@RestController //domyslnie dziala tutaj @ResponseBody
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
    public List<ProductDto> getProducts() {
        List<Product> products = productRepository.findAll();
        ProductMapper productMapper = new ProductMapper();
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product p : products) {
            ProductDto productDto = productMapper.map(p);
            productDtos.add(productDto);
        }
        return productDtos;
    }

    @GetMapping("products/{serialNo}")
    public ProductDto getProduct(@PathVariable String serialNo) {
        Optional<Product> optionalProduct = productRepository.findBySerialNo(serialNo);
        if (optionalProduct.isPresent()) {
            Optional<Product> result = productRepository.findById(optionalProduct.get().getId());

            ProductMapper productMapper = new ProductMapper();

            return productMapper.map(result.get()); //zwraca ResultDto
        } else {
            return new ProductDto();
        }

    }

//    @RequestMapping(value = "products", method = RequestMethod.POST)
//    public ResponseEntity<Product> add(@Valid @RequestBody Product product) {
//        productRepository.save(product);
//        return new ResponseEntity<>(product, HttpStatus.OK);
//    }

    @RequestMapping(value = "products/search", method = RequestMethod.GET)
    public List<ProductDto> findProductsByName(@RequestParam(value = "name") String name) {
        List<Product> products = productRepository.findByProductsName(name);
        ProductMapper productMapper = new ProductMapper();
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product p : products) {
            ProductDto productDto = productMapper.map(p);
            productDtos.add(productDto);
        }

        return productDtos;
    }

    @RequestMapping(value = "products", method = RequestMethod.POST)
    public ResponseEntity<Product> addProduct(
            @RequestParam(value = "category") String category,
            @RequestParam(value = "producer") String producer,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "pieces") String pieces,
            @RequestParam(value = "price") String price,
            @RequestParam(value = "promotion") boolean promotion,
            @RequestParam(value = "serialNo") String serialNo,
            @RequestParam(value = "picture") String picture) {

        Optional<Product> optionalProduct = productRepository.findBySerialNo(serialNo);
        Optional<Category> optionalCategory = categoryRepository.findOneByTitle(category);
        Optional<Producer> optionalProducer = producerRepository.findByTitle(producer);

        if (!optionalProduct.isPresent() && optionalCategory.isPresent() && optionalProducer.isPresent()) {

            Product product = new Product();
            product.setName(name);
            product.setDescription(description);
            product.setCategory(optionalCategory.get());
            product.setPrice(new BigDecimal(price));
            product.setProducer(optionalProducer.get());
            product.setPieces(Integer.valueOf(pieces));
            product.setPromotion(promotion);
            product.setSerialNo(serialNo);
            product.setPicture(picture);

            productRepository.save(product);

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "products/{serialNo}", method = RequestMethod.PUT)
    public ResponseEntity<Product> updateProduct(@PathVariable String serialNo,
                                                 @RequestParam(value = "description", required = false) String description,
                                                 @RequestParam(value = "name", required = false) String name,
                                                 @RequestParam(value = "price", required = false) String price,
                                                 @RequestParam(value = "pieces", required = false) String pieces,
                                                 @RequestParam(value = "promotion", required = false) boolean promotion,
                                                 @RequestParam(value = "picture", required = false) String picture) {

        Optional<Product> productOptional = productRepository.findBySerialNo(serialNo);

        if (!productOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            Product product = productOptional.get();

            product.setId(product.getId());
            product.setName(name);
            product.setDescription(description);
            product.setPrice(new BigDecimal(price));
            product.setPieces(Integer.valueOf(pieces));
            product.setPromotion(promotion);
            product.setPicture(picture);

            productRepository.save(product);

            return ResponseEntity.noContent().build();
        }
    }

//    @RequestMapping(value = "products/update", method = RequestMethod.PUT)
//    @ResponseBody
//    public ResponseEntity<Product> updateProd(@Valid @RequestBody Product product) {
//
//        Optional<Product> productOptional = productRepository.findBySerialNo(product.getSerialNo());
//
//        if (!productOptional.isPresent()) {
//            return ResponseEntity.notFound().build();
//        } else {
//            Product productNew = productOptional.get();
//
//            productNew.setId(product.getId());
//            productNew.setName(product.getName());
//            productNew.setDescription(product.getDescription());
//            productNew.setPrice(product.getPrice());
//            productNew.setPieces(product.getPieces());
//            productNew.setPromotion(product.isPromotion());
//
//            productRepository.save(productNew);
//
//            return ResponseEntity.noContent().build();
//        }
//    }


    @RequestMapping(value = "products/{serialNo}", method = RequestMethod.DELETE)
    public ResponseEntity<Product> deleteProduct(@PathVariable String serialNo) {
        Optional<Product> productOptional = productRepository.findBySerialNo(serialNo);

        if (!productOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            productRepository.deleteById(productOptional.get().getId());
            return ResponseEntity.noContent().build();
        }
    }


}
