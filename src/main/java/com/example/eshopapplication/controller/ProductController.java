package com.example.eshopapplication.controller;

import com.example.eshopapplication.service.ProductService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/all")
    private String getAllProducts(Model model){
        model.addAttribute("products",productService.findAll());
        return "product/all-products";
    }
    @GetMapping("/add")
    private String getAddProductPage(){
        return "product/add-products";
    }
    @PostMapping("/add")
    private String getAddProductPage(@RequestParam String name,
                                     @RequestParam String description,
                                     @RequestParam Integer quantity,
                                     @RequestParam Double price){
        System.out.println(name+" " + description);
        System.out.println(quantity+" " + price);
        productService.save(name,description,quantity,price);
        System.out.println(productService.findAll());
        return "redirect:/products/all";
    }
    @GetMapping("/edit/{id}")
    private String getProductById(@PathVariable Long id,Model model){
        if(productService.findById(id).isPresent()){
            model.addAttribute("product",productService.findById(id));
            return "product/add-products";
        }
        return "product/add-products";
    }
    @PostMapping("/delete/{id}")
    private String deleteProductById(@PathVariable Long id){
        productService.deleteById(id);
        return "redirect:/products/all";
    }
}
