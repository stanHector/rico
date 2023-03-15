package service.ricotunes.giftcards.service;

import org.springframework.stereotype.Service;
import service.ricotunes.giftcards.model.Transactions;

import java.util.List;

@Service
public interface TransactionService {
  Transactions addTransactions(Transactions transactions);
  List<Transactions> getAllTransactions();
}
