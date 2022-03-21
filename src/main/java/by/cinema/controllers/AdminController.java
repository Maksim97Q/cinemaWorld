package by.cinema.controllers;

import by.cinema.entities.User;
import by.cinema.services.TicketService;
import by.cinema.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {
    private static final String ADMIN = "admin";
    private static final String REDIRECT_ADMIN = "redirect:/Admin";
    private static final String UPDATE_USER = "updateUser";
    private static final String USER_TICKET = "user_tickets";

    private UserService userService;
    private TicketService ticketService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/Admin")
    @Secured(value = "ROLE_ADMIN")
    public String userList(Model model, @RequestParam(value = "nameFilter", required = false) String nameFilter) {
        model.addAttribute("allUsers", userService.allUsersWithFilter(nameFilter));
        model.addAttribute("nameFilter", nameFilter);
        return ADMIN;
    }

    @GetMapping("/Admin/delete/{id}")
    @Secured(value = "ROLE_ADMIN")
    public String deleteUser(@PathVariable(value = "id") Long userId) {
        userService.deleteUser(userId);
        return REDIRECT_ADMIN;
    }

    @GetMapping("/Admin/edit/{id}")
    @Secured(value = "ROLE_ADMIN")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("userForm", user);
        return UPDATE_USER;
    }

    @PostMapping("/Admin/update/{id}")
    @Secured(value = "ROLE_ADMIN")
    public String updateStudent(@PathVariable("id") Long id, User user, Model model) {
        userService.saveUser(user);
        return REDIRECT_ADMIN;
    }

    @GetMapping("/User_Tickets")
    @Secured(value = "ROLE_ADMIN")
    public String showUserTickets(Model model) {
        model.addAttribute("users_with_tickets", ticketService.findByAll());
        return USER_TICKET;
    }
}