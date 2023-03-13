package service.ricotunes.giftcards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import service.ricotunes.giftcards.model.WalletTransactions;

import java.util.List;

@Repository
public interface WithdrawRepository extends JpaRepository<WalletTransactions, Long> {
    List<WalletTransactions>findAllByUserId(Long userId);

    WalletTransactions findByUserId(Long userId);

}
