package com.example.eshopapplication.web;

import com.example.eshopapplication.entity.Category;
import com.example.eshopapplication.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping
    private String getAllCategories(Model model){
        model.addAttribute("categories",categoryService.listCategories());
        model.addAttribute("body_content","category/category");
        return "master-template";
    }
    @GetMapping("/create")
    private String addCategoryPage(Model model){
//        model.addAttribute("categories",categoryService.listCategories());
        model.addAttribute("body_content","category/createCategory");
        return "master-template";
    }
    @PostMapping("/create")
    private String addCategory(@RequestParam String name,
                               @RequestParam String description){
        categoryService.create(name,description);
        return "redirect:/category";
    }
}
