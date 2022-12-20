package service.ricotunes.giftcards.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import service.ricotunes.giftcards.model.Banks;
import service.ricotunes.giftcards.repository.BankRepository;
import service.ricotunes.giftcards.service.BankService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class BankController {
    private final BankService bankService;
    private final BankRepository bankRepository;

    @GetMapping("banks")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public List<Banks> getBanks(){
        return bankRepository.findAll();
    }

    @PostMapping("add-bank")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity createBank(@RequestBody Banks bank){
        return bankService.addBank(bank);
    }
}
