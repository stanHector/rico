package service.ricotunes.giftcards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import service.ricotunes.giftcards.model.Category;
import service.ricotunes.giftcards.model.Country;
import service.ricotunes.giftcards.model.GiftCard;

@Repository
public interface GiftCardRepository extends JpaRepository<GiftCard,Long>{

    GiftCard findByCategory(Category category);
//    GiftCard findByCountry(Country currency);
//
//    GiftCard findByRate(double cardRate);

}
