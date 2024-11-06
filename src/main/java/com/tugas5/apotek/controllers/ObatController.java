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
import com.tugas5.apotek.models.Obat;
import com.tugas5.apotek.models.Supplier;
import com.tugas5.apotek.services.CategoryService;
import com.tugas5.apotek.services.ObatService;
import com.tugas5.apotek.services.SupplierService;

@Controller
public class ObatController {

    @Autowired
    private ObatService obatService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SupplierService supplierService;

    @GetMapping("obat-home")
    public String listObat(Model model) {
        List<Obat> obatList = obatService.getAllObat();
        model.addAttribute("obatList", obatList);
        return "home"; // Mengembalikan ke halaman home
    }

    @GetMapping("/add-obat")
    public String showAddObatForm(Model model) {
        Obat obat = new Obat();
        model.addAttribute("obat", obat);

        // Ambil daftar kategori dan supplier dari layanan (service)
        List<Category> categories = categoryService.getAllCategories();
        List<Supplier> suppliers = supplierService.getAllSuppliers();

        model.addAttribute("categories", categories);
        model.addAttribute("suppliers", suppliers);

        return "add-obat";
    }

    @PostMapping("save-obat")
    public String saveObat(@ModelAttribute("obat") Obat obat) {
        // Pastikan kategori dan supplier di-set sebelum menyimpan
        obatService.save(obat);
        return "redirect:/obat-home"; // Redirect setelah menyimpan obat
    }

    @GetMapping("delete-obat/{id}")
    public String deleteObat(@PathVariable("id") Integer id) {
        obatService.deleteById(id);
        return "redirect:/obat-home"; 
    }

    @GetMapping("/update-obat/{id}")
public String showUpdateObatForm(@PathVariable("id") Integer id, Model model) {
    Obat obat = obatService.findById(id); // Assume there's a method to find Obat by ID
    if (obat != null) {
        model.addAttribute("obat", obat);

        // Ambil daftar kategori dan supplier dari layanan (service)
        List<Category> categories = categoryService.getAllCategories();
        List<Supplier> suppliers = supplierService.getAllSuppliers();

        model.addAttribute("categories", categories);
        model.addAttribute("suppliers", suppliers);

        return "update-obat"; // Return the update form
    }
    return "redirect:/obat-home"; // Redirect if Obat not found
}

@PostMapping("/update-obat/{id}")
public String updateObat(@PathVariable("id") Integer id, @ModelAttribute("obat") Obat obat) {
    // Find existing Obat and update its fields
    Obat existingObat = obatService.findById(id);
    if (existingObat != null) {
        existingObat.setNamaObat(obat.getNamaObat());
        existingObat.setPrice(obat.getPrice());
        existingObat.setDescription(obat.getDescription());
        
        existingObat.setCategory(obat.getCategory());
        existingObat.setSupplier(obat.getSupplier());

        obatService.save(existingObat); 
    }
    return "redirect:/obat-home"; 
}

}
