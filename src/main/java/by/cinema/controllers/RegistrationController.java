package by.cinema.controllers;

import by.cinema.entities.User;
import by.cinema.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    private static final String REGISTRATION = "registration";
    private static final String INDEX = "index";

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/Registration")
    public String registration(Model model) {
        model.addAttribute("userAdd", new User());
        return REGISTRATION;
    }

    @PostMapping("/Registration")
    public String addUser(@ModelAttribute("userAdd") User user, Model model) {
        boolean user_boolean = userService.saveUser(user);
        if (user_boolean) {
            model.addAttribute("save_user", true);
            return INDEX;
        } else {
            model.addAttribute("notSave", userService.saveUser(user));
            return REGISTRATION;
        }
    }
}