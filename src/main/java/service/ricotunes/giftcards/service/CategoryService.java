package service.ricotunes.giftcards.service;

import org.springframework.stereotype.Service;
import service.ricotunes.giftcards.model.Category;

import java.util.List;

@Service
public interface CategoryService {
    Category addCategory(Category category);
    List<Category> getCategories();
}
