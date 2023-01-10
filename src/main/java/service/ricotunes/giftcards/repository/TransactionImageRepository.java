package service.ricotunes.giftcards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import service.ricotunes.giftcards.model.TransactionImage;

@Repository
public interface TransactionImageRepository extends JpaRepository<TransactionImage, Long> {
}
