package service.ricotunes.giftcards.service;

import org.springframework.stereotype.Service;
import service.ricotunes.giftcards.model.Banks;

@Service
public interface BankService {

    Banks addBank(Banks bank);
}
