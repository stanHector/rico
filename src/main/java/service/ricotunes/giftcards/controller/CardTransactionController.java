package service.ricotunes.giftcards.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import service.ricotunes.giftcards.dto.CardTransactionDto;
import service.ricotunes.giftcards.exception.ResourceNotFoundException;
import service.ricotunes.giftcards.model.CardTransactions;
import service.ricotunes.giftcards.repository.CardTransactionRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class CardTransactionController {
    private final CardTransactionRepository cardTransactionRepository;

    @GetMapping("transactions")
    @PreAuthorize("hasRole('ADMIN')")
    List<CardTransactions> getCardTransactions() {
        return cardTransactionRepository.findAll();
    }


    @GetMapping("transactions/user/{userId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<CardTransactions> getTransactionsByUserId(@PathVariable(value = "userId") Long userId) throws ResourceNotFoundException {
        CardTransactions cardTransactions = cardTransactionRepository.findByUserId(userId);
        if (cardTransactions == null) {
            throw new ResourceNotFoundException("Transaction not found for this userId: " + userId);
        }
        return ResponseEntity.ok().body(cardTransactions);
    }

    @PostMapping("transaction")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<Object> createCardTransaction(@Valid @RequestBody CardTransactions cardTransactions) {

        double cardQuantity = cardTransactions.getQuantity();
        System.out.println("Quantity:: " + cardQuantity);
        double buyingRate = cardTransactions.getGiftCard().getRate();
        System.out.println("Buying Rate: " + buyingRate);
        double totalAmount = buyingRate * cardQuantity;
        cardTransactions.setAmount(totalAmount);
        return new ResponseEntity<>(cardTransactionRepository.save(cardTransactions), HttpStatus.OK);
    }

    //update an account
    @PutMapping("transaction/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public CardTransactions updateCardTransaction(@PathVariable("id") Long id, @Valid @RequestBody CardTransactionDto cardTransactionDto) throws
            ResourceNotFoundException {
        System.out.println("Update Card Transaction with ID = " + id + "...");
        CardTransactions cardTransactions = cardTransactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Card Transactions not found for this id: " + id));
        cardTransactions.setStatus(cardTransactionDto.getStatus());
        final CardTransactions updatedCardTransactions = cardTransactionRepository.save(cardTransactions);
        System.out.println("Updated CardTransactions " + updatedCardTransactions);
        return cardTransactionRepository.save(updatedCardTransactions);
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
    @DeleteMapping("card-transaction/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String, Boolean> deleteAccount(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        CardTransactions cardTransactions = cardTransactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Card Transactions not found for this id: " + id));
        cardTransactionRepository.delete(cardTransactions);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}