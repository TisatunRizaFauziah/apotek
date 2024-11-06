package com.tugas5.apotek.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.tugas5.apotek.models.Supplier;
import com.tugas5.apotek.services.SupplierService;

@Controller
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping("supplier")
    public String supplierList(Model model) {
        List<Supplier> suppliers = supplierService.getAllSuppliers();
        model.addAttribute("suppliers", suppliers);
        return "supplier";
    }

    @GetMapping("add-supplier")
    public String addSupplierForm(Model model) {
        Supplier supplier = new Supplier();
        model.addAttribute("supplier", supplier);
        return "add-supplier";
    }

    @PostMapping("save-supplier")
    public String saveSupplier(@ModelAttribute("supplier") Supplier supplier) {
        supplierService.save(supplier);
        return "redirect:/supplier";
    }

    @GetMapping("delete-supplier/{id}")
    public String deleteSupplier(@PathVariable(value = "id") Integer id) {
        supplierService.deleteById(id);
        return "redirect:/supplier";
    }

    @GetMapping("update-supplier/{id}")
    public String showUpdateSupplierForm(@PathVariable("id") Integer id, Model model) {
        Supplier supplier = supplierService.findById(id);
        model.addAttribute("supplier", supplier);
        return "update-supplier";
    }

    @PostMapping("update-supplier/{id}")
    public String updateSupplier(@PathVariable(value = "id") Integer id, @ModelAttribute("supplier") Supplier supplier) {
        Supplier existingSupplier = supplierService.findById(id);
        if (existingSupplier != null) {
            existingSupplier.setName(supplier.getName());
            existingSupplier.setAddress(supplier.getAddress());
            existingSupplier.setPhone(supplier.getPhone());
            supplierService.save(existingSupplier);
        }
        return "redirect:/supplier";
    }
}