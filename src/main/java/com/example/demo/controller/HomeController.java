package com.example.demo.controller;


import com.example.demo.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @Autowired
    ProductController productController;

    // @RequestMapping(value = "home", method = RequestMethod.GET)
    public String home() {
        return "index";
    }

    @RequestMapping(value = "products", method = RequestMethod.GET)
    public String messages(Model model) {
        model.addAttribute("products", productController.products());
        return "products";
    }


}
