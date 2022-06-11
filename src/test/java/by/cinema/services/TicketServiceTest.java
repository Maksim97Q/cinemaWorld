package by.cinema.services;

import by.cinema.entities.BankCard;
import by.cinema.entities.Movie;
import by.cinema.entities.Ticket;
import by.cinema.entities.User;
import by.cinema.repositories.BankCardRepository;
import by.cinema.repositories.TicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class TicketServiceTest {
    @Mock
    private static TicketRepository ticketRepository;

    @InjectMocks
    private static TicketService ticketService;

    @Mock
    private static UserService userService;

    @Mock
    private static BankCardRepository bankCardRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByAllTicketUser() {
        List<Ticket> ticketList = new ArrayList<>();
        Ticket ticket = new Ticket(1L, 55, new Movie(), new User(1L, "max", "555"));
        ticketList.add(ticket);
        when(ticketRepository.findAll()).thenReturn(ticketList);
        ticketService.findByAllTicketUser(1L);
        List<Ticket> all = ticketRepository.findAll();
        assertEquals(all.get(0).getUser().getId(), 1L);
    }

    @Test
    void findByAll() {
        List<Ticket> ticketList = new ArrayList<>();
        Ticket ticket = new Ticket(1L, 55, new Movie(), new User());
        ticketList.add(ticket);
        when(ticketRepository.findAll()).thenReturn(ticketList);
        List<Ticket> byAll = ticketService.findByAll();
        assertNotNull(byAll);
        assertEquals(1, ticketList.size());
    }

    @Test
    void findByIdTicket() {
        Ticket ticket = new Ticket(1L, 55, new Movie(), new User());
        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));
        Ticket byIdTicket = ticketService.findByIdTicket(1L);
        assertNotNull(byIdTicket);
    }

    @Test
    void deleteTicketById() {
        BankCard bankCard = new BankCard(1L, 66L, 10, "active", true, new User());
        User user = new User(1L, "max", "5555");
        Ticket ticket = new Ticket(1L, 55,
                new Movie(1L, "nova", "12-12-2022", 7, 5), new User());
        when(userService.getUser_log()).thenReturn(user);
        when(bankCardRepository.findByUserIdAndForPayment(user.getId())).thenReturn(bankCard);
        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));
        BankCard byUserIdAndForPayment = bankCardRepository.findByUserIdAndForPayment(user.getId());
        Optional<Ticket> byId = ticketRepository.findById(1L);
        assertNotNull(byUserIdAndForPayment);
        assertTrue(byId.isPresent());
        Integer getBalance = byUserIdAndForPayment.getBalance() + byId.get().getMovies().getPrice();
        byUserIdAndForPayment.setBalance(getBalance);
        ticketService.deleteTicketById(1L);
        verify(ticketRepository, times(1)).deleteById(1L);
    }
}