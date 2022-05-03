package by.cinema.repositories;

import by.cinema.entities.BankCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankCardRepository extends JpaRepository<BankCard, Long> {
    @Query("select b from BankCard b where b.user.id = ?1")
    List<BankCard> findByUserId(Long user_id);

    @Query("select b from BankCard b where b.status = 'inactive'")
    List<BankCard> findAllByStatus();

    @Query("select b from BankCard b where b.user.id = ?1 and b.forPayment = true")
    BankCard findByUserIdAndForPayment(Long id_user);

    @Query("select b from BankCard b where b.cardNumber = ?1")
    BankCard findByCardNumber(Long number);
}
