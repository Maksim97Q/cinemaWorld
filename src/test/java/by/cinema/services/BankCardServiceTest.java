package by.cinema.services;

import by.cinema.entities.BankCard;
import by.cinema.entities.User;
import by.cinema.repositories.BankCardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class BankCardServiceTest {
    @Mock
    private static BankCardRepository bankCardRepository;

    @InjectMocks
    private static BankCardService bankCardService;

    @Mock
    private static UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByAllCardUser() {
        List<BankCard> bankCardList = new ArrayList<>();
        bankCardList.add(new BankCard(1L, 5L, 10, "", false, new User(2L)));
        when(bankCardService.findByAllCardUser(2L)).thenReturn(bankCardList);
        List<BankCard> bankCard = bankCardService.findByAllCardUser(bankCardList.get(0).getUser().getId());
        assertNotNull(bankCard);
    }

    @Test
    void deleteCard() {
        BankCard bankCard1 = new BankCard(1L, 66L, 10, "inactive", false, new User());
        when(bankCardRepository.findById(bankCard1.getId())).thenReturn(Optional.of(bankCard1));
        assertEquals("inactive", bankCard1.getStatus());
        bankCardService.deleteCard(bankCard1.getId());
        verify(bankCardRepository, times(1)).deleteById(bankCard1.getId());
    }

    @Test
    void saveCard() {
        BankCard bankCard1 = new BankCard(1L, null, 10, "", false, new User());
        bankCardService.saveCard(bankCard1);
        BankCard byCardNumber = bankCardRepository.findByCardNumber(bankCard1.getCardNumber());
        assertNull(byCardNumber);
        bankCard1.setUser(userService.getUser_log());
        bankCard1.setBalance(55);
        bankCard1.setStatus("inactive");
        bankCard1.setForPayment(false);
        verify(bankCardRepository, times(1)).save(bankCard1);

    }

    @Test
    void findById() {
        BankCard bankCard1 = new BankCard(1L, 5L, 10, "", false, new User());
        when(bankCardRepository.findById(1L)).thenReturn(Optional.of(bankCard1));
        assertEquals(bankCardRepository.findById(1L), Optional.of(bankCard1));
        verify(bankCardRepository, times(1)).findById(1L);
        BankCard bankCard = bankCardService.findById(1L);
        assertNotNull(bankCard);

    }

    @Test
    void findByAll() {
        List<BankCard> bankCardList = new ArrayList<>();
        bankCardList.add(new BankCard(1L, 5L, 10, "", false, new User()));
        bankCardList.add(new BankCard(1L, 3L, 6, "", false, new User()));
        when(bankCardService.findByAll()).thenReturn(bankCardList);
        List<BankCard> bankCardList1 = bankCardService.findByAll();
        assertNotNull(bankCardList1);
        assertEquals(2, bankCardList1.size());
    }

    @Test
    void getActivationCard() {
        List<BankCard> bankCardList = new ArrayList<>();
        bankCardList.add(new BankCard(1L, 5L, 10, "inactive", false, new User()));
        bankCardService.getActivationCard(anyLong());
        BankCard byId = bankCardService.findById(bankCardList.get(0).getId());
        byId.setStatus("active");
        bankCardRepository.save(byId);
        assertEquals("active", byId.getStatus());
        verify(bankCardRepository, times(1)).save(byId);
    }

    @Test
    void findAllInactiveCard() {
        List<BankCard> bankCardList = new ArrayList<>();
        bankCardList.add(new BankCard(1L, 5L, 10, "inactive", false, new User()));
        when(bankCardService.findAllInactiveCard()).thenReturn(bankCardList);
        List<BankCard> bankCardList1 = bankCardService.findAllInactiveCard();
        assertEquals("inactive", bankCardList1.get(0).getStatus());
    }

    @Test
    void findByIdForPayment() {
//        BankCard bankCard1 = new BankCard(1L, 66L, 10, "active", true, new User());
//        BankCard bankCard2 = new BankCard(1L, 66L, 10, "active", true, new User());
//        when(bankCardRepository.findById(bankCard1.getId())).thenReturn(Optional.of(bankCard1));
//        when(bankCardRepository.findByUserIdAndForPayment(userService.getUser_log().getId()))
//                .thenReturn(bankCard2);
//        assertNotNull(bankCard2);
//        assertEquals("active", bankCard1.getStatus());
//        bankCardService.findByIdForPayment(bankCard1.getId());
//        verify(bankCardRepository, times(1)).save(bankCard1);
    }
}