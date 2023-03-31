package service.ricotunes.giftcards.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import service.ricotunes.giftcards.model.WalletTransactions;
import service.ricotunes.giftcards.repository.WalletRepository;
import service.ricotunes.giftcards.repository.WithdrawRepository;
import service.ricotunes.giftcards.service.WithdrawService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Transactional
@Service
public class WalletTransactionServiceImpl implements WithdrawService {
    private final WithdrawRepository withdrawRepository;
    private  final WalletRepository walletRepository;


    @Override
    public WalletTransactions withdrawFund(WalletTransactions walletTransactions) {
            return withdrawRepository.save(walletTransactions);
    }

    @Override
    public List<WalletTransactions> getWithdraws() {
        return withdrawRepository.findAll();
    }

    @Override
    public List<WalletTransactions> getWithdrawsByUserId(Long userId) {
        return withdrawRepository.findAllByUserId(userId);
    }

    @Override
    public Optional<WalletTransactions> getWithdrawById(Long id) {
        return withdrawRepository.findById(id);
    }
}
