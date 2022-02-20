package by.cinema.controllers;

import by.cinema.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/User")
    public String userShow(Model model) {
        return "user";
    }

    @PostMapping("/User")
    public String userSend() {
        return "user";
    }
}
