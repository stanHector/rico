package service.ricotunes.giftcards.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import service.ricotunes.giftcards.exception.ResourceNotFoundException;
import service.ricotunes.giftcards.model.Country;
import service.ricotunes.giftcards.repository.CountryRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class CountryController {

    private final CountryRepository countryRepository;

    @GetMapping("country")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    List<Country> getCountries(){
        return  countryRepository.findAll();
    }


    //get country by Id
    @GetMapping("country/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Country> getCountryById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Country not found for this id :: " + id));
        return ResponseEntity.ok().body(country);
    }

    @PostMapping("country")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<Object> createCountry(@Valid @RequestBody Country country){
        return  new ResponseEntity<>(countryRepository.save(country), HttpStatus.CREATED);
    }

    //delete country
    @DeleteMapping("country/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String, Boolean> deleteCountry(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Country not found for this id: " + id));
        countryRepository.delete(country);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
