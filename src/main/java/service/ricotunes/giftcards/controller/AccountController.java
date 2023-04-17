package service.ricotunes.giftcards.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import service.ricotunes.giftcards.dto.AccountDto;
import service.ricotunes.giftcards.exception.AccountExistsException;
import service.ricotunes.giftcards.exception.ResourceNotFoundException;
import service.ricotunes.giftcards.model.Account;
import service.ricotunes.giftcards.repository.AccountRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class AccountController {

    private final AccountRepository accountRepository;

    //get all accounts
    @GetMapping("/account")
    @PreAuthorize("hasRole('ADMIN')")
    List<Account> getAccounts() {
        return accountRepository.findAll();
    }


    @GetMapping("/account/user/{userId}")
    public List<Account> getAccountByUserId(@PathVariable Long userId) {
        return accountRepository.getAllByUserId(userId);
    }

    //create an account
    @PostMapping("/account")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    ResponseEntity<Object> createAccount(@Valid @RequestBody Account account) throws AccountExistsException {
        Account accountNumber = accountRepository.findByAccountNumber(account.getAccountNumber());
        if (accountNumber != null) {
            throw new AccountExistsException(String.format("Account with account number %s already exist", account.getAccountNumber()));
        }
        return new ResponseEntity<>(accountRepository.save(account), HttpStatus.valueOf(200));
    }


    //update an account
    @PutMapping("account/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Account updateAccount(@PathVariable("id") Long id, @Valid @RequestBody AccountDto accountDto) throws ResourceNotFoundException {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found for this id: " + id));
        account.setAccountNumber(accountDto.getAccountNumber());
        account.setAccountName(accountDto.getAccountName());
        account.setBankName(accountDto.getBankName());
        final Account updatedAccount = accountRepository.save(account);
        return accountRepository.save(updatedAccount);
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
    @DeleteMapping("/account/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String, Boolean> deleteAccount(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found for this id: " + id));
        accountRepository.delete(account);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

//    delete an account by userId
    @DeleteMapping("/account/bank/{userId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public Map<String, Boolean> deleteUserBankAccount(@PathVariable(value = "userId") Long userId) throws ResourceNotFoundException {
        Account account = accountRepository.findByUserId(userId);
        if (account == null) {
            throw new ResourceNotFoundException("Account not found for this userId: " + userId);
        }
        accountRepository.delete(account);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
