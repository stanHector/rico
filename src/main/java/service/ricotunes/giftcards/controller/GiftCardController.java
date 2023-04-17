package service.ricotunes.giftcards.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import service.ricotunes.giftcards.dto.GiftCardDto;
import service.ricotunes.giftcards.exception.ResourceNotFoundException;
import service.ricotunes.giftcards.model.GiftCard;
import service.ricotunes.giftcards.repository.GiftCardRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class GiftCardController {

    private final GiftCardRepository giftCardRepository;

    @GetMapping("cards")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    List<GiftCard> getCards() {
        return giftCardRepository.findAll();
    }

    //get card by Id
    @GetMapping("card/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GiftCard> getCardById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        GiftCard giftCard = giftCardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Card not found for this id: " + id));
        System.out.println("Card Rate:: " + giftCard.getCardRate());
        return ResponseEntity.ok().body(giftCard);
    }

    //create a card
    @PostMapping("card")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<GiftCard> createCard(@Valid @RequestBody GiftCard giftCard) {
        double cardRate = giftCard.getCardRate();
        String denomination = giftCard.getCategory().getCategoryName();
        System.out.println("Denomination:::: " + denomination);

        double adminRate = cardRate * giftCard.getRmbRate();
        double actualRate = adminRate - giftCard.getProfit();
//        double roundedRate = Math.round(actualRate);
        System.out.println("rate ::: " + actualRate);
        System.out.println("admin Rate::" + adminRate);
        System.out.println("Profit :: " + giftCard.getProfit());
        giftCard.setDenomination(denomination);
        giftCard.setRate(actualRate);
        giftCard.setAdminRate(adminRate);
        return new ResponseEntity<>(giftCardRepository.save(giftCard), HttpStatus.OK);
    }


    //update a card
    @PutMapping("card/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public GiftCard updateCard(@PathVariable("id") Long id, @Valid @RequestBody GiftCardDto giftCardDto) throws ResourceNotFoundException {
        System.out.println("Update Card with ID = " + id + "...");
        GiftCard giftcard = giftCardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Card not found for this id: " + id));
        giftcard.setName(giftCardDto.getName());
        giftcard.setType(giftCardDto.getType());
        giftcard.setCardRate(giftCardDto.getCardRate());
        giftcard.setRmbRate(giftCardDto.getRmbRate());
        giftcard.setProfit(giftCardDto.getProfit());
        giftcard.setDenomination(giftcard.getCategory().getCategoryName());
        final GiftCard updatedGiftCard = giftCardRepository.save(giftcard);
        System.out.println("Updated Card " + updatedGiftCard);
        return giftCardRepository.save(updatedGiftCard);
    }

    //delete card
    @DeleteMapping("card/{id}")
    @PreAuthorize(" hasRole('ADMIN')")
    public Map<String, Boolean> deleteCard(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        GiftCard giftcard = giftCardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Card not found for this id: " + id));
        giftCardRepository.delete(giftcard);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}