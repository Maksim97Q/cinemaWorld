package by.cinema.services;

import by.cinema.entities.Ticket;
import by.cinema.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService {
    private TicketRepository ticketRepository;

    @Autowired
    public void setTicketRepository(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> findByAllTicketUser(Long id_user) {
        List<Ticket> allTicket = ticketRepository.findAll();
        return allTicket.stream()
                .filter(p -> p.getUser().getId().equals(id_user))
                .collect(Collectors.toList());
    }

    public List<Ticket> findByAll() {
        return ticketRepository.findAll();
    }

//    public void deleteTicketById(Long id) {
//        ticketRepository.deleteById(id);
//    }
}
