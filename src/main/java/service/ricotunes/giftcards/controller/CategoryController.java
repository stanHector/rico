package service.ricotunes.giftcards.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import service.ricotunes.giftcards.dto.CategoryDto;
import service.ricotunes.giftcards.exception.ResourceNotFoundException;
import service.ricotunes.giftcards.model.Category;
import service.ricotunes.giftcards.repository.CategoryRepository;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class CategoryController {
    private final CategoryRepository categoryRepository;

    @GetMapping("category")
    @PreAuthorize("hasRole('USER')or hasRole('ADMIN')")
    ResponseEntity<?> getCategories() {
        return new ResponseEntity<>(categoryRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("category")
    @PreAuthorize("hasRole('ADMIN')")
    public Category createCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    @PutMapping("category/{id}")
    @PreAuthorize("hasRole('USER')or hasRole('ADMIN')")
    public Category updateCategory(@PathVariable("id") Long id, @Valid @RequestBody CategoryDto categoryDto) throws ResourceNotFoundException {
        System.out.println("Update category with ID = " + id + "...");
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found for this id: " + id));
//        category.setCardRate(categoryDto.getCardRate());
        final Category updatedCategory = categoryRepository.save(category);
        System.out.println("Updated category " + updatedCategory);
        return categoryRepository.save(updatedCategory);
    }
}
