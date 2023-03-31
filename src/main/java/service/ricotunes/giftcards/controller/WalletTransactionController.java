package service.ricotunes.giftcards.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ricotunes.giftcards.model.Wallet;
import service.ricotunes.giftcards.model.WalletTransactions;
import service.ricotunes.giftcards.payload.response.Response;
import service.ricotunes.giftcards.repository.WalletRepository;
import service.ricotunes.giftcards.service.WithdrawService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class WalletTransactionController {
    private final WithdrawService withdrawService;
    private final WalletRepository walletRepository;

    @PostMapping("wallet-transactions/{userId}")
    ResponseEntity<Object> createWithdraw(@PathVariable(value = "userId") Long userId, @RequestBody WalletTransactions walletTransactions) {
        Wallet wallet = walletRepository.findByUserId(userId);
        double balance = wallet.getCurrentBalance();

        System.out.println("Balance: " + balance);
        double amount = walletTransactions.getAmount();
        if (balance == 0.0) {
            return new ResponseEntity<>(new Response(405, "Insufficient fund in wallet with balance", walletTransactions ), HttpStatus.OK);
//            throw new InsufficientBalanceException(String.format("Insufficient fund in wallet with balance %s ", walletTransactions.getAmount()));
        } else if (balance < amount) {
            return new ResponseEntity<>(new Response(401, "Amount withdrawn can not be greater than wallet balance", walletTransactions), HttpStatus.OK);
//            throw new BadRequestException(String.format("Amount withdrawn can not be greater than wallet balance %s ", walletTransactions.getAmount()));
        } else if (amount < 0) {
            return new ResponseEntity<>(new Response(400, "Amount withdrawn can not be negative", walletTransactions), HttpStatus.OK);
//            throw new BadRequestException(String.format("Amount withdrawn can not be negative %s ", walletTransactions.getAmount()));
        } else {
            double newBalance = balance - amount;
            walletTransactions.setBalance(newBalance);
            walletTransactions.setTransactionType(walletTransactions.getTransactionType());
            System.out.println("Remaining balance: " + newBalance);
        }
        if (wallet != null) {
            wallet.setCurrentBalance(walletTransactions.getBalance());
        }
        return new ResponseEntity<>(withdrawService.withdrawFund(walletTransactions), HttpStatus.CREATED);
    }
}
