package service.ricotunes.giftcards.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import service.ricotunes.giftcards.exception.BadRequestException;
import service.ricotunes.giftcards.exception.InsufficientBalanceException;
import service.ricotunes.giftcards.model.Withdraw;
import service.ricotunes.giftcards.repository.WithdrawRepository;
import service.ricotunes.giftcards.service.WithdrawService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Transactional
@Service
public class WithdrawServiceImpl implements WithdrawService {
    private final WithdrawRepository withdrawRepository;


    @Override
    public Withdraw withdrawFund(Withdraw withdraw) {
        double balance = withdraw.getWallet().getCurrentBalance();
        double amount = withdraw.getAmount();
        double initialBalance = withdraw.getWallet().getCurrentBalance();
        if (balance == 0.0) {
            throw new InsufficientBalanceException(String.format("Insufficient fund in wallet with balance %s ", balance));
        } else if (balance < amount) {
            throw new BadRequestException(String.format("Amount withdrawn can not be greater than wallet balance %s ", amount));
        } else if (amount < 0) {
            throw new BadRequestException(String.format("Amount withdrawn can not be negative %s ", amount));
        } else {
            double newBalance = balance - amount;
            withdraw.setRemainingBalance(newBalance);
            withdraw.getWallet().setCurrentBalance(withdraw.getRemainingBalance());
            withdraw.setInitialBalance(initialBalance);
            System.out.println("Remaining balance: " + newBalance);
            return withdrawRepository.save(withdraw);
        }

    }

    @Override
    public List<Withdraw> getWithdraws() {
        return withdrawRepository.findAll();
    }

    @Override
    public List<Withdraw> getWithdrawsByUserId(Long userId) {
        return withdrawRepository.findAllByUserId(userId);
    }

    @Override
    public Optional<Withdraw> getWithdrawById(Long id) {
        return withdrawRepository.findById(id);
    }
}
