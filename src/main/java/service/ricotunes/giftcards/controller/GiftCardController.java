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
@CrossOrigin(origins = "http://localhost:3001")
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
        return ResponseEntity.ok().body(giftCard);
    }


    //create a card
    @PostMapping("card")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<GiftCard> createCard(@Valid @RequestBody GiftCard giftCard) {
        double multiplyRate = giftCard.getRmbRate() * giftCard.getCardRate();
        double actualRate = multiplyRate - giftCard.getProfit();
        giftCard.setRate(actualRate);
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
        giftcard.setCategory(giftCardDto.getCategory());
        giftcard.setRmbRate(giftCardDto.getRmbRate());
        double multiplyRate = giftcard.getRmbRate() * giftcard.getCardRate();
        double actualRate = multiplyRate - giftcard.getProfit();
        giftcard.setRate(actualRate);
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