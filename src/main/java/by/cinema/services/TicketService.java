package by.cinema.services;

import by.cinema.entities.Ticket;
import by.cinema.entities.User;
import by.cinema.repositories.MovieRepository;
import by.cinema.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> findByAllTicket(Long id_user) {
        List<Ticket> allTicket = ticketRepository.findAll();
        return allTicket.stream().filter(p -> p.getUser().getId().equals(id_user)).collect(Collectors.toList());
    }
}
