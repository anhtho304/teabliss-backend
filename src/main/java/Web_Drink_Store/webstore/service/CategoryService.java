package Web_Drink_Store.webstore.service;

import Web_Drink_Store.webstore.entity.Category;
import Web_Drink_Store.webstore.enums.CategoryStatus;
import Web_Drink_Store.webstore.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public List<Category> getAll() {
        return categoryRepository.findByStatusAndIsDeletedFalse(
                CategoryStatus.ACTIVE
        );
    }


    public Category create(Category category) {
        category.setStatus(CategoryStatus.ACTIVE);
        category.setDeleted(false);
        return categoryRepository.save(category);
    }


    public Category update(Long id, Category newCategory) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        category.setName(newCategory.getName());
        category.setDescription(newCategory.getDescription());

        return categoryRepository.save(category);
    }

    public void delete(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        category.setStatus(CategoryStatus.INACTIVE);
        category.setDeleted(true);

        categoryRepository.save(category);
    }
}