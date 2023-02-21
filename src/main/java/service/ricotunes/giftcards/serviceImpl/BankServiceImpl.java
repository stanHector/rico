package service.ricotunes.giftcards.serviceImpl;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import service.ricotunes.giftcards.exception.AlreadyExistException;
import service.ricotunes.giftcards.model.Banks;
import service.ricotunes.giftcards.repository.BankRepository;
import service.ricotunes.giftcards.service.BankService;

@AllArgsConstructor
@Service
public class BankServiceImpl implements BankService {
    private final BankRepository bankRepository;


    @Override
   public Banks addBank(Banks bank) throws AlreadyExistException {
        Banks bankName = bankRepository.findByName(bank.getName());
        Banks bankCode = bankRepository.findBybankCode(bank.getBankCode());
        if (bankName != null) {
            throw new AlreadyExistException(String.format("Bank with name %s already exist", bank.getName()));
        } else if (bankCode != null) {
            throw new AlreadyExistException(String.format("Bank with bank code %s already exist", bank.getBankCode()));
        } else {
            return bankRepository.save(bank);
        }
    }

//    @Override
//    public List<Banks> getAllBanks() {
//        return bankRepository.findAll();
//    }
}
