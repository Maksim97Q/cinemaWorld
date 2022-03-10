package by.cinema.controllers;

import by.cinema.entities.User;
import by.cinema.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    private static final String REGISTRATION = "registration";
    private static final String INDEX = "index";

    @Autowired
    private UserService userService;

    @GetMapping("/Registration")
    public String registration(Model model) {
        model.addAttribute("userAdd", new User());
        return REGISTRATION;
    }

    @PostMapping("/Registration")
    public String addUser(@ModelAttribute("userAdd") User user, Model model) {
        if (userService.saveUser(user)) {
            return INDEX;
        } else {
            model.addAttribute("notSave", userService.saveUser(user));
            return REGISTRATION;
        }
    }
}