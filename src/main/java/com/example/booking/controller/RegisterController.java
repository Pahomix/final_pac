package com.example.booking.controller;

import com.example.booking.model.User;
import com.example.booking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        try {
            userService.registerUser(user);
            return "redirect:/login"; // Перенаправление на страницу входа после успешной регистрации
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage()); // Добавление сообщения об ошибке в модель
            return "register"; // Показ страницы регистрации с сообщением об ошибке
        }
    }
}
