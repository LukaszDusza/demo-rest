package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

}
