package by.cinema.services;

import by.cinema.entities.BankCard;
import by.cinema.repositories.BankCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BankCardService {
    private BankCardRepository bankCardRepository;
    private UserService userService;

    @Autowired
    public void setBankCardRepository(BankCardRepository bankCardRepository) {
        this.bankCardRepository = bankCardRepository;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public List<BankCard> findByAllCardUser(Long id_user) {
        List<BankCard> findCard = bankCardRepository.findByUserId(id_user);
        return findCard.stream()
                .filter(p -> p.getUser().getId().equals(id_user))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteCard(Long id) {
        BankCard byId = findById(id);
        if (byId.getStatus().equals("inactive")) {
            bankCardRepository.deleteById(id);
        }
    }

    @Transactional
    public void saveCard(BankCard bankCard) {
        bankCard.setUser(userService.getUser_log());
        bankCard.setBalance(100);
        bankCard.setStatus("inactive");
        bankCardRepository.save(bankCard);
    }

    public BankCard findById(Long id) {
        Optional<BankCard> findMovie = bankCardRepository.findById(id);
        return findMovie.orElse(new BankCard());
    }

    public List<BankCard> findByAll() {
        return bankCardRepository.findAll();
    }

    @Transactional
    public void getActivationCard(Long card_id) {
        BankCard byId = findById(card_id);
        byId.setStatus("active");
        bankCardRepository.save(byId);
    }

    public List<BankCard> findAllInactiveCard() {
        List<BankCard> byAll = findByAll();
        return byAll.stream().filter(p -> p.getStatus().equals("inactive"))
                .collect(Collectors.toList());
    }
}
