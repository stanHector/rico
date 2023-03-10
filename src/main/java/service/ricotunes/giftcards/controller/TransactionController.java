package service.ricotunes.giftcards.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import service.ricotunes.giftcards.dto.TransactionDto;
import service.ricotunes.giftcards.exception.ResourceNotFoundException;
import service.ricotunes.giftcards.model.Transactions;
import service.ricotunes.giftcards.repository.TransactionRepository;
import service.ricotunes.giftcards.service.TransactionService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class TransactionController {
    private final TransactionRepository transactionRepository;
    private final TransactionService transactionService;

    @GetMapping("transactions")
    @PreAuthorize("hasRole('ADMIN')")
    List<Transactions> getTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("transaction/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    ResponseEntity<Transactions> getTransactionsById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Transactions transactions = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transactions not found for this id :: " + id));
        return ResponseEntity.ok().body(transactions);
    }

    @PostMapping("transaction")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<Object> createTransaction(@Valid @RequestBody Transactions transactions) {
        return new ResponseEntity<>(transactionService.addTransactions(transactions), HttpStatus.CREATED);
    }

    //update an account
    @PutMapping("transaction/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public Transactions updateCardTransaction(@PathVariable("id") Long id, @Valid @RequestBody TransactionDto transactionDto) throws
            ResourceNotFoundException {
        System.out.println("Update Card Transaction with ID = " + id + "...");
        Transactions transactions = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Card Transactions not found for this id: " + id));
        transactions.setStatus(transactionDto.getStatus());
        transactions.setRemarks(transactionDto.getRemark());
        transactions.setAmount(transactionDto.getAmount());
        final Transactions updatedTransactions = transactionRepository.save(transactions);
        System.out.println("Updated CardTransactions " + updatedTransactions);
        return transactionRepository.save(updatedTransactions);
    }

    @GetMapping("transactions/user/{userId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public List<Transactions> getTransactionsByUserId(@PathVariable Long userId) throws ResourceNotFoundException {
        Transactions transactions = transactionRepository.findByUserId(userId);
        if (transactions == null) {
            throw new ResourceNotFoundException("Transactions not found for this userId :: " + userId);
        }
        return transactionRepository.getAllByUserId(userId);
    }


    //transfer fund from wallet
//    @PostMapping("/api/v1/account/{accountId}/wallet/{walletId}/withdraw")
//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
//    public ResponseEntity<ServiceResponse> withdraw(@PathVariable("accountId") long accountId, @PathVariable("walletId") long walletId, @RequestBody double amount) throws ResourceNotFoundException {
//        ServiceResponse response = new ServiceResponse();
//        Account account = accountRepository.withdrawFromAccount(walletId, accountId);
//
//        Wallet wallet = walletRepository.findById(walletId)
//                .orElseThrow(() -> new ResourceNotFoundException("Account not found for this id: " + walletId));
//
//        if (wallet.getCurrentBalance() == 0) {
//            throw new InsufficientBalanceException(String.format("Balance in wallet with id  %s Insufficient", walletId));
//        }
//        if (wallet.getCurrentBalance() < amount) {
//            throw new InsufficientBalanceException("The amount entered is less than balance in the wallet");
//        } else {
//            response.setStatus("200");
//            response.setData(account);
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        }
//    }

    //delete an account
    @DeleteMapping("transaction/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String, Boolean> deleteTransaction(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Transactions transactions = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Card Transactions not found for this id: " + id));
        transactionRepository.delete(transactions);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
