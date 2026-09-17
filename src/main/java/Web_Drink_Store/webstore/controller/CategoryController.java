package Web_Drink_Store.webstore.controller;

import Web_Drink_Store.webstore.dto.CategoryDTO;
import Web_Drink_Store.webstore.entity.Category;
import Web_Drink_Store.webstore.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody CategoryDTO dto) {
        Category category = new Category();
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        return ResponseEntity.ok(categoryService.create(category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(
            @PathVariable Long id,
            @RequestBody CategoryDTO dto) {

        Category category = new Category();
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());

        return ResponseEntity.ok(categoryService.update(id, category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}