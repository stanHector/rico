package service.ricotunes.giftcards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import service.ricotunes.giftcards.model.RequestRates;

import java.util.List;

@Repository
public interface RequestRateRepository extends JpaRepository<RequestRates, Long> {

    RequestRates findByUserId(Long userId);
    List<RequestRates> getAllByUserId(Long userId);
}