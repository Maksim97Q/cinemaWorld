package by.cinema.controllers;

import by.cinema.entities.User;
import by.cinema.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/Registration")
    public String registration(Model model) {
        model.addAttribute("userAdd", new User());
        return "registration";
    }

    @PostMapping("/Registration")
//    public String addUser(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model) {
    public String addUser(@ModelAttribute("userAdd") User user, Model model) {
        model.addAttribute("if", userService.saveUser(user));
        if (userService.saveUser(user)) {
            return "index";
        } else {
            return "registration";
        }
//        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())){
//            model.addAttribute("passwordError", "Пароли не совпадают");
//            return "registration";
//        }
//        if (!userService.saveUser(userForm)){
//            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
//            return "registration";
//        }
    }
}