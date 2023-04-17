package service.ricotunes.giftcards.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import service.ricotunes.giftcards.model.WalletTransactions;
import service.ricotunes.giftcards.service.EmailsService;

import javax.mail.MessagingException;
import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class EmailController {

    private final EmailsService emailService;

    @PostMapping("sendEmail")
    public String send(@Valid @RequestBody WalletTransactions walletTransactions) throws MessagingException {
        emailService.sendEmailWithAttachment(walletTransactions);
        return "Email sent successfully";
    }
}
