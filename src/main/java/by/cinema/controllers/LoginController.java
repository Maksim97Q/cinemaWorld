package by.cinema.controllers;

import by.cinema.entities.User;
import by.cinema.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    private static final String LOGIN = "login";
    private static final String INDEX = "index";

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login() {
        return LOGIN;
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        return LOGIN;
    }

    @GetMapping("/")
    public String indexPage(Model model) {
        User user_log = userService.getUser_log();
        model.addAttribute("user_log", user_log.getUsername());
        return INDEX;
    }
}
