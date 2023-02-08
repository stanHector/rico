package service.ricotunes.giftcards.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import service.ricotunes.giftcards.model.Category;
import service.ricotunes.giftcards.repository.CategoryRepository;
import service.ricotunes.giftcards.service.CategoryService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;


    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }
}
