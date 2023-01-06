package service.ricotunes.giftcards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import service.ricotunes.giftcards.model.Account;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByAccountNumber(String accountNumber);
    List<Account> getAllByUserId(Long userId);
    Account findByUserId(Long userId);
//    Account findByAccountName(String accountName);

//    Account withdrawFromAccount(long walletId, long accountId);
}
