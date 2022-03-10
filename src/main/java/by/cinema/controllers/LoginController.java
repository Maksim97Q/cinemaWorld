package by.cinema.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    private static final String LOGIN = "login";

    @GetMapping("/login")
    public String login() {
        return LOGIN;
    }

    @PostMapping("/login")
    public String loginPage() {
        return LOGIN;
    }
}
