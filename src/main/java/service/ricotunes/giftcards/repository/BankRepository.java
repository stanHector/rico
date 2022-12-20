package service.ricotunes.giftcards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import service.ricotunes.giftcards.model.Banks;

@Repository
public interface BankRepository extends JpaRepository<Banks, Long> {
    Banks findByName(String name);
    Banks findBybankCode(String bankCode);
}
