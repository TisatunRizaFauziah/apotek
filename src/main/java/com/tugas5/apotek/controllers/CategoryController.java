package com.tugas5.apotek.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.tugas5.apotek.models.Category;
import com.tugas5.apotek.services.CategoryService;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("category")
    public String categoryList(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "category";
    }

    @GetMapping("add-category")
    public String addCategoryForm(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        return "add-category";
    }

    @PostMapping("save-category")
    public String saveCategory(@ModelAttribute("category") Category category) {
        categoryService.save(category);
        return "redirect:/category";
    }

    @GetMapping("delete-category/{id}")
    public String deleteCategory(@PathVariable(value = "id") Integer id) {
        categoryService.deleteById(id);
        return "redirect:/category";
    }

    @GetMapping("update-category/{id}")
    public String showUpdateCategoryForm(@PathVariable("id") Integer id, Model model) {
        Category category = categoryService.findById(id);
        model.addAttribute("category", category);
        return "update-category";
    }

    @PostMapping("update-category/{id}")
    public String updateCategory(@PathVariable(value = "id") Integer id, @ModelAttribute("category") Category category) {
        Category existingCategory = categoryService.findById(id);
        if (existingCategory != null) {
            existingCategory.setName(category.getName());
            existingCategory.setDescription(category.getDescription());
            categoryService.save(existingCategory);
        }
        return "redirect:/category";
    }
}
