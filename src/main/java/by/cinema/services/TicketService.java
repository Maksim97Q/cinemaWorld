package by.cinema.services;

import by.cinema.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    private TicketRepository ticketRepository;

    @Autowired
    public void setTicketRepository(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }
}
