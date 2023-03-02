package service.ricotunes.giftcards.service;

import org.springframework.stereotype.Service;
import service.ricotunes.giftcards.model.Withdraw;

import java.util.List;
import java.util.Optional;

@Service
public interface WithdrawService {

    Withdraw withdrawFund(Withdraw withdraw);

    List<Withdraw> getWithdraws();

    List<Withdraw> getWithdrawsByUserId(Long userId);

    Optional<Withdraw> getWithdrawById(Long id);
}
