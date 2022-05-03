package by.cinema.controllers;

import by.cinema.entities.BankCard;
import by.cinema.entities.User;
import by.cinema.services.BankCardService;
import by.cinema.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BankCardController {
    private static final String CARD = "card";
    private static final String REDIRECT_CARD = "redirect:/Card";
    private static final String CARD_ACTIVATION = "cardActivation";
    private static final String REDIRECT_CARD_USER_CARD = "redirect:/Card/users_card";

    private UserService userService;
    private BankCardService bankCardService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setBankCardService(BankCardService bankCardService) {
        this.bankCardService = bankCardService;
    }

    @GetMapping("/Card")
    public String getCard(Model model) {
        User user_log = userService.getUser_log();
        model.addAttribute("cardAdd", new BankCard());
        List<BankCard> byAllCardUser = bankCardService.findByAllCardUser(user_log.getId());
        model.addAttribute("card_new", byAllCardUser);
        return CARD;
    }

    @PostMapping("/Card")
    public String card(@ModelAttribute(value = "cardAdd") BankCard bankCard, Model model) {
        bankCardService.saveCard(bankCard);
        return REDIRECT_CARD;
    }

    @GetMapping("Card/delete/{id}")
    public String deleteCard(@PathVariable(value = "id") Long id, Model model) {
        bankCardService.deleteCard(id);
        return REDIRECT_CARD;
    }

    @GetMapping("Card/activation/{id}")
    @Secured(value = "ROLE_ADMIN")
    public String activationCard(@PathVariable(value = "id") Long card_id, Model model) {
        bankCardService.getActivationCard(card_id);
        return REDIRECT_CARD_USER_CARD;
    }

    @GetMapping("Card/choose_for_buy/{id}")
    public String chooseCard(@PathVariable(value = "id") Long card_id, Model model) {
        bankCardService.findByIdForPayment(card_id);
        return REDIRECT_CARD;
    }

    @GetMapping("Card/users_card")
    @Secured(value = "ROLE_ADMIN")
    public String inactiveCardUser(Model model) {
        List<BankCard> allInactiveCard = bankCardService.findAllInactiveCard();
        model.addAttribute("activationCard", allInactiveCard);
        return CARD_ACTIVATION;
    }

}
