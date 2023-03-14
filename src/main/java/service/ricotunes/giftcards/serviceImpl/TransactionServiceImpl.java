package service.ricotunes.giftcards.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import service.ricotunes.giftcards.model.Transactions;
import service.ricotunes.giftcards.repository.TransactionRepository;
import service.ricotunes.giftcards.service.TransactionService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;


    @Override
    public Transactions addTransactions(Transactions transactions) {
        Transactions transaction = new Transactions();
        transaction.setDate(transactions.getDate());
        transaction.setQuantity(transactions.getQuantity());
        transaction.setAmount(transactions.getAmount());
        transaction.setImageList(transactions.getImageList());
        transaction.setType(transactions.getGiftCard().getType());
        transaction.setReceivingAmount(transactions.getReceivingAmount());
        transaction.setUserId(transactions.getUserId());
        transaction.setGiftCard(transactions.getGiftCard());
        return transactionRepository.save(transactions);
    }

    @Override
    public List<Transactions> getAllTransactions() {
        return transactionRepository.findAll();
    }
}
