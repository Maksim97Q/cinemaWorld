package by.cinema.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TicketController {
    @GetMapping("/Ticket")
    public String getTicket() {
        return "ticket";
    }

    @PostMapping("/Ticket")
    public String postTicket() {
        return "ticket";
    }
}
