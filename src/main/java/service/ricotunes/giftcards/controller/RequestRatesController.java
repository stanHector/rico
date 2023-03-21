package service.ricotunes.giftcards.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import service.ricotunes.giftcards.dto.RequestRatesDto;
import service.ricotunes.giftcards.exception.ResourceNotFoundException;
import service.ricotunes.giftcards.model.RequestRates;
import service.ricotunes.giftcards.repository.RequestRateRepository;
import service.ricotunes.giftcards.service.RequestRatesService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("request/{id}")
    ResponseEntity<Object> getRequestById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        RequestRates requestRates = requestRateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Request not found for this id :: " + id));
        return ResponseEntity.ok().body(requestRates);
    }

    //get all requests by a user
    @GetMapping("request/user/{userId}")
    public List<RequestRates> getRequestsByUserId(@PathVariable Long userId) {
        return requestRateRepository.getAllByUserId(userId);
    }

    //update request
    @PutMapping("request/{id}")
    @PreAuthorize("hasRole('USER')or hasRole('ADMIN')")
    public RequestRates updateRequest(@PathVariable("id") Long id, @Valid @RequestBody RequestRatesDto requestRatesDto) throws ResourceNotFoundException {
        RequestRates requestRates = requestRateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Request not found for this id: " + id));
        requestRates.setComment(requestRatesDto.getComment());
        requestRates.setAmount(requestRatesDto.getAmount());
        final RequestRates updatedRates = requestRateRepository.save(requestRates);
//        System.out.println("Updated User " + updatedRates);
        return requestRateRepository.save(updatedRates);
    }

    //delete request
    @DeleteMapping("request/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String, Boolean> deleteRequestRates(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        RequestRates requestRates = requestRateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Request not found for this id: " + id));
        requestRateRepository.delete(requestRates);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
