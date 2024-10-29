package com.tugas5.apotek.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tugas5.apotek.models.Login;
import com.tugas5.apotek.services.LoginService;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginservice;

    @GetMapping("registrasi")
    public String registrasi(Model model) {
        Login login = new Login();
        model.addAttribute("regis", login);
        return "registrasi";
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @GetMapping("home") // Mengubah path untuk menghindari konflik
    public String home(Model model) {
        List<Login> logins = loginservice.getAllLogin();
        model.addAttribute("logins", logins);
        return "home";
    }

    @PostMapping("save-regis")
    public String saveRegid(@ModelAttribute("regis") Login login) {
        loginservice.save(login);
        return "redirect:/login"; // Mengalihkan ke halaman login setelah registrasi
    }

    @PostMapping("cek-login")
    public String cekLogin(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        Login login = loginservice.findByUsernameAndPassword(username, password);
        if (login != null) {
            model.addAttribute("login", login);
            return "home"; // Jika login berhasil, arahkan ke home
        } else {
            return "redirect:/login"; // Jika gagal, kembali ke halaman login
        }
    }

    @GetMapping("delete-akun/{id}")
    public String deleteAkun(@PathVariable(value = "id") Integer id) {
        loginservice.deleteById(id);
        return "redirect:/home"; // Pastikan ini sesuai dengan path yang ada
    }
}
