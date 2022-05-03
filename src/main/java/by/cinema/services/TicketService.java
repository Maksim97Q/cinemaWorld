package by.cinema.services;

import by.cinema.entities.BankCard;
import by.cinema.entities.Ticket;
import by.cinema.entities.User;
import by.cinema.repositories.BankCardRepository;
import by.cinema.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketService {
    private TicketRepository ticketRepository;
    private BankCardRepository bankCardRepository;
    private UserService userService;

    @Autowired
    public void setTicketRepository(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Autowired
    public void setBankCardRepository(BankCardRepository bankCardRepository) {
        this.bankCardRepository = bankCardRepository;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
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

    public Ticket findByIdTicket(Long id) {
        Optional<Ticket> byId = ticketRepository.findById(id);
        return byId.orElse(new Ticket());
    }

    @Transactional
    public void deleteTicketById(Long id) {
        User user_log = userService.getUser_log();
        BankCard byUserIdAndForPayment = bankCardRepository.findByUserIdAndForPayment(user_log.getId());
        Ticket byIdTicket = findByIdTicket(id);
        Integer getBal = byUserIdAndForPayment.getBalance() + byIdTicket.getMovies().getPrice();
        byUserIdAndForPayment.setBalance(getBal);
        ticketRepository.deleteById(id);
    }
}
