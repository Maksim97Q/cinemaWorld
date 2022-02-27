package by.cinema.controllers;

import by.cinema.entities.User;
import by.cinema.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;


    @GetMapping("/Admin")
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.allUsers());
        return "admin";
    }

    @GetMapping("/Admin/delete/{id}")
    public String deleteUser(@PathVariable(value = "id") Long userId) {
        userService.deleteUser(userId);
        return "redirect:/Admin";
    }

    @GetMapping("/Admin/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("userForm", user);
        return "updateUser";
    }

    @PostMapping("/Admin/update/{id}")
    public String updateStudent(@PathVariable("id") long id, User user, Model model) {
        userService.saveUser(user);
        return "redirect:/Admin";
    }
}