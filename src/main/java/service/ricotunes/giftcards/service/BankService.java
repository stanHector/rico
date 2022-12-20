package service.ricotunes.giftcards.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import service.ricotunes.giftcards.model.Banks;

@Service
public interface BankService {

    ResponseEntity addBank(Banks bank);
}
