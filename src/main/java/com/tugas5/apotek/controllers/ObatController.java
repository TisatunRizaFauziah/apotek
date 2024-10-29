package com.tugas5.apotek.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.tugas5.apotek.models.Obat;
import com.tugas5.apotek.services.ObatService;


@Controller
public class ObatController {

    @Autowired
    private ObatService obatService;

    @GetMapping("obat-home")
    public String listObat(Model model) {
        List<Obat> obatList = obatService.getAllObat();
        model.addAttribute("obatList", obatList);
        return "home"; // Mengembalikan ke halaman home
    }

    @GetMapping("add-obat")
    public String showFormTambahObat(Model model) {
        Obat obat = new Obat();
        model.addAttribute("obat", obat);
        return "add-obat"; // Mengembalikan ke halaman form tambah obat
    }

    @PostMapping("save-obat")
    public String saveObat(@ModelAttribute("obat") Obat obat) {
        obatService.save(obat);
        return "redirect:obat-home"; // Redirect setelah menyimpan obat
    }

    @GetMapping("delete-obat/{id}")
    public String deleteObat(@PathVariable("id") Integer id) {
        obatService.deleteById(id);
        return "redirect:/obat-home"; 
    }
}
