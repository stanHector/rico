package service.ricotunes.giftcards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import service.ricotunes.giftcards.model.WalletTransactions;

@Repository
public interface WalletTransactionsRepository extends JpaRepository<WalletTransactions, Long> {
}