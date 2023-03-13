package service.ricotunes.giftcards.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import service.ricotunes.giftcards.exception.ResourceNotFoundException;
import service.ricotunes.giftcards.model.RequestRates;
import service.ricotunes.giftcards.repository.RequestRateRepository;
import service.ricotunes.giftcards.service.RequestRatesService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/")
public class RequestRatesController {
    private final RequestRatesService requestRatesService;
    private final RequestRateRepository requestRateRepository;


    @GetMapping("requests")
    List<RequestRates> getRequestRates() {
        return requestRatesService.getRequest();
    }

    @PostMapping("request")
    ResponseEntity<Object> createRequestRates(@RequestBody RequestRates requestRates) {
        return new ResponseEntity<>(requestRatesService.addRequest(requestRates), HttpStatus.CREATED);
    }

    @GetMapping("/request/{id}")
    ResponseEntity<Object> getRequestById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        RequestRates requestRates = requestRateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Request not found for this id :: " + id));
        return ResponseEntity.ok().body(requestRates);
    }

    @GetMapping("requests/user/{userId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public List<RequestRates> getRequestRatesByUserId(@PathVariable Long userId) throws ResourceNotFoundException {
        RequestRates requestRates = requestRateRepository.findByUserId(userId);
        if (requestRates == null) {
            throw new ResourceNotFoundException("Transactions not found for this userId :: " + userId);
        }
        return requestRateRepository.getAllByUserId(userId);
    }
}
