package service.ricotunes.giftcards.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import service.ricotunes.giftcards.model.Banks;
import service.ricotunes.giftcards.repository.BankRepository;
import service.ricotunes.giftcards.service.BankService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
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

    @PostMapping("bank")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> createBank(@RequestBody Banks bank){
        return new ResponseEntity<>(bankService.addBank(bank), HttpStatus.CREATED);
    }
}