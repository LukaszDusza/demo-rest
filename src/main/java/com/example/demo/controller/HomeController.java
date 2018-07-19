package com.example.demo.controller;


import com.example.demo.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @Autowired
    ProductController productController;

    @Autowired
    CategoryController categoryController;

    @Autowired
    ProducerController producerController;

    public String home() {
        return "index";
    }

    @RequestMapping(value = "products", method = RequestMethod.GET)
    public String products(Model model) {
        model.addAttribute("products", productController.getProducts());
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

    // ======================================== add ====================================================================
    @RequestMapping(value = "add-form", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("categories", categoryController.categories());
        model.addAttribute("products", productController.getProducts());
        model.addAttribute("producers", producerController.producers());
        return "add";
    }

    // @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model,
                      @RequestParam(value = "category") String category, @RequestParam(value = "producer") String producer,
                      @RequestParam(value = "name") String name, @RequestParam(value = "description") String description,
                      @RequestParam(value = "pieces") String pieces, @RequestParam(value = "price") String price,
                      @RequestParam(value = "promotion") boolean promotion, @RequestParam(value = "serialNo") String serialNo) {
        productController.addProduct(category, producer, name, description, pieces, price, promotion, serialNo);
        return products(model);
    }

    // ======================================== delete =================================================================

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(Model model,
                         @RequestParam(value = "del") String serialNo) {
        productController.deleteProduct(serialNo);
        return products(model);
    }


    // ======================================== update ==================================================================

    @RequestMapping(value = "update-form", method = RequestMethod.GET)
    public String updateSite(Model model, String serialNo) {
        model.addAttribute("product", productController.getProduct(serialNo));
        return "updatePage";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(Model model,
                         @RequestParam(value = "serialNo") String serialNo,
                         @RequestParam(value = "description", required = false) String description,
                         @RequestParam(value = "name", required = false) String name,
                         @RequestParam(value = "price", required = false) String price,
                         @RequestParam(value = "pieces", required = false) String pieces,
                         @RequestParam(value = "promotion", required = false) boolean promotion) {
        productController.updateProduct(serialNo, description, name, price, pieces, promotion);
        return products(model);
    }


}
