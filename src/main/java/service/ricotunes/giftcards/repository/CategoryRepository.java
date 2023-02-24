package service.ricotunes.giftcards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import service.ricotunes.giftcards.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
