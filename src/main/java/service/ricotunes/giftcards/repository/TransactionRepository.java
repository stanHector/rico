package service.ricotunes.giftcards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import service.ricotunes.giftcards.model.Transactions;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Long> {

    Transactions findByUserId(Long userId);

    Transactions getByUserId(Long userId);

    List<Transactions> getAllByUserId(Long userId);

//    List<Transactions> findByUserId(Long userId);
}
