package service.ricotunes.giftcards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import service.ricotunes.giftcards.model.CardTransactions;
import service.ricotunes.giftcards.model.GiftCard;

@Repository
public interface CardTransactionRepository extends JpaRepository<CardTransactions, Long> {
    CardTransactions findByGiftCard(GiftCard giftCard);

    CardTransactions findByUserId(Long userId);
}
