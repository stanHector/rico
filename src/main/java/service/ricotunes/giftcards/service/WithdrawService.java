package service.ricotunes.giftcards.service;

import org.springframework.stereotype.Service;
import service.ricotunes.giftcards.model.WalletTransactions;

import java.util.List;
import java.util.Optional;

@Service
public interface WithdrawService {

    WalletTransactions withdrawFund(WalletTransactions walletTransactions);

    List<WalletTransactions> getWithdraws();

    List<WalletTransactions> getWithdrawsByUserId(Long userId);

    Optional<WalletTransactions> getWithdrawById(Long id);
}
