package by.cinema.controllers;

import by.cinema.entities.Ticket;
import by.cinema.entities.User;
import by.cinema.services.TicketService;
import by.cinema.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {
    private static final String PROFILE = "profile";
    private static final String REDIRECT_PROFILE_TICKETS = "redirect:/Profile/tickets";

    @Autowired
    private UserService userService;

    @Autowired
    private TicketService ticketService;

    @GetMapping("/Profile/tickets")
    public String showUserTickets(Model model) {
        User user_log = userService.getUser_log();
        List<Ticket> ticketByUserId = ticketService.findByAllTicket(user_log.getId());
        model.addAttribute("user_newTicket", ticketByUserId);
        return PROFILE;
    }

    @GetMapping("/Profile")
    public String showUser() {
        User user_log = userService.getUser_log();
        if (user_log.getTicket() != null) {
            return REDIRECT_PROFILE_TICKETS;
        } else {
            return PROFILE;
        }
    }

    @PostMapping("/Profile")
    public String userSend() {
        return PROFILE;
    }
}
