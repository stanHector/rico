package service.ricotunes.giftcards.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import service.ricotunes.giftcards.model.Country;

@Repository
public interface CountryRepository  extends JpaRepository<Country, Long> {
}
