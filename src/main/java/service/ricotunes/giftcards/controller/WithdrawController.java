package service.ricotunes.giftcards.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.ricotunes.giftcards.model.Withdraw;
import service.ricotunes.giftcards.service.WithdrawService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class WithdrawController {
    private final WithdrawService withdrawService;

    @PostMapping("/withdraw")
    ResponseEntity<Object> createWithdraw(@RequestBody Withdraw withdraw){
        return new ResponseEntity<>(withdrawService.withdrawFund(withdraw), HttpStatus.CREATED);
    }
}
