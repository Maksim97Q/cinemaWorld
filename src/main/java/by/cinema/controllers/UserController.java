package by.cinema.controllers;

import by.cinema.entities.User;
import by.cinema.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private static final String USER = "user";

    @Autowired
    UserService userService;

    @GetMapping("/User")
    public String userShow(Model model) {
        User user_log = userService.getUser_log();
        model.addAttribute("user_log", user_log.getUsername());
        return USER;
    }

    @PostMapping("/User")
    public String userSend() {
        return USER;
    }
}
