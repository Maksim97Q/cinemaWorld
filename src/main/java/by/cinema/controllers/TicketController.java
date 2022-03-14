package by.cinema.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TicketController {
    @GetMapping("/Schedule")
    public String getTicket() {
        return "schedule";
    }

    @PostMapping("/Schedule")
    public String postTicket() {
        return "schedule";
    }
}
