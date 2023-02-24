package service.ricotunes.giftcards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import service.ricotunes.giftcards.model.GiftCard;

@Repository
public interface GiftCardRepository extends JpaRepository<GiftCard,Long>{
}