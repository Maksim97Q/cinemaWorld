package by.cinema.controllers;

import by.cinema.entities.Ticket;
import by.cinema.entities.User;
import by.cinema.repositories.TicketRepository;
import by.cinema.services.MovieService;
import by.cinema.services.TicketService;
import by.cinema.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TicketController {
    private static final String TICKET = "ticket";
    private static final String REDIRECT_TICKET = "redirect:/Movie";

    @Autowired
    private TicketService ticketService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private UserService userService;

//    @GetMapping("/Movie/buy/{seats}")
//    public String getTicket(@PathVariable(value = "seats") Integer seats, Model model) {
//        model.addAttribute("seats", seats);
//        String username = userService.getUser_log().getUsername();
//        User byUsername = userService.findByUsername(username);
////        ticketService.saveTicket(new Ticket(seats, 33));
//        byUsername.setTicket(new Ticket(seats, 40));
//        return TICKET;
//    }

    @PostMapping("/Ticket")
    public String postTicket() {
        return TICKET;
    }
}
