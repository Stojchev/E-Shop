package com.example.eshopapplication.web;

import com.example.eshopapplication.entity.ShoppingCart;
import com.example.eshopapplication.entity.exception.ProductAlreadyInShoppingCartException;
import com.example.eshopapplication.entity.exception.ProductNotFoundException;
import com.example.eshopapplication.service.ProductService;
import com.example.eshopapplication.service.ShoppingCartService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ShoppingCartService shoppingCartService;

    public ProductController(ProductService productService, ShoppingCartService shoppingCartService) {
        this.productService = productService;
        this.shoppingCartService = shoppingCartService;
    }


    @GetMapping("/all")
    private String getAllProducts(Model model){
        model.addAttribute("products",productService.findAll());
        model.addAttribute("body_content","product/all-products");
        return "master-template";
    }
    @GetMapping("/add")
    private String getAddProductPage(Model model){
        model.addAttribute("body_content","product/add-products");
        return "master-template";
    }
    @PostMapping("/add")
    private String getAddProductPage(@RequestParam String name,
                                     @RequestParam String description,
                                     @RequestParam Integer quantity,
                                     @RequestParam Double price){
//        System.out.println(name+" " + description);
//        System.out.println(quantity+" " + price);
        productService.save(name,description,quantity,price);
//        System.out.println(productService.findAll());
        return "redirect:/products/all";
    }
    @GetMapping("/edit/{id}")
    private String getProductById(@PathVariable Long id,Model model){
        model.addAttribute("body_content","product/add-products");
        if(productService.findById(id).isPresent()){
            model.addAttribute("product",productService.findById(id));
            return "master-template";
        }
        return "master-template";
    }
    @GetMapping("/accessDenied")
    private String asd(Model model){
        model.addAttribute("body_content","product/add-products");
        return "master-template";
    }
    @PostMapping("/delete/{id}")
    private String deleteProductById(@PathVariable Long id){
        productService.deleteById(id);
        return "redirect:/products/all";
    }
    @PostMapping("/addToCart/{id}")
    private String addProductToCart(@PathVariable Long id, HttpServletRequest req) throws ProductAlreadyInShoppingCartException, ProductNotFoundException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username=  auth.getName();
        shoppingCartService.addProductToShoppingCart(username,id);
        return "redirect:/products/shopping-cart";
    }
    @GetMapping("/shopping-cart")
    private String getShoppingCart(HttpServletRequest req,Model model){
        String username=req.getRemoteUser();
        ShoppingCart shoppingCart=shoppingCartService.getActiveShoppingCart(username);
        model.addAttribute("products",this.shoppingCartService.listAllProductsInShoppingCart(shoppingCart.getId()));
        model.addAttribute("body_content","shopping-cart");
        return "master-template";
    }

}
