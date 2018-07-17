package com.example.demo.controller;


import com.example.demo.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @Autowired
    ProductController productController;

    @Autowired
    CategoryController categoryController;

    @Autowired
    ProducerController producerController;

    // @RequestMapping(value = "home", method = RequestMethod.GET)
    public String home() {
        return "index";
    }

    @RequestMapping(value = "products", method = RequestMethod.GET)
    public String products(Model model) {
        model.addAttribute("products", productController.products());
        return "products";
    }

    @RequestMapping(value = "categories", method = RequestMethod.GET)
    public String categories(Model model) {
        model.addAttribute("categories", categoryController.categories());
        return "categories";
    }

    @RequestMapping(value = "producers", method = RequestMethod.GET)
    public String producers(Model model) {
        model.addAttribute("producers", producerController.producers());
        return "producers";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("categories", categoryController.categories());
        model.addAttribute("products",  productController.products());
        model.addAttribute("producers", producerController.producers());
        return "add";
    }

    @RequestMapping(value = "add-new", method = RequestMethod.POST)
    @ResponseBody
    public String add(@RequestParam(value = "category") String category, @RequestParam(value = "producer") String producer,
                          @RequestParam(value = "name") String name, @RequestParam(value = "description") String description,
                          @RequestParam(value = "pieces") String pieces, @RequestParam(value = "price") String price,
                          @RequestParam(value = "promotion") boolean promotion) {
        productController.add(category,producer,name,description,pieces,price,promotion);

        return "OK";
    }

}
