package Web_Drink_Store.webstore.controller;

import Web_Drink_Store.webstore.dto.ProductDTO;
import Web_Drink_Store.webstore.entity.Category;
import Web_Drink_Store.webstore.entity.Product;
import Web_Drink_Store.webstore.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> search(@RequestParam String name) {
        return ResponseEntity.ok(productService.search(name));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getByCategory(
            @PathVariable Long categoryId) {
        return ResponseEntity.ok(productService.getByCategory(categoryId));
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody ProductDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setStockQuantity(dto.getStockQuantity());
        product.setDescription(dto.getDescription());
        product.setImageUrl(dto.getImageUrl());

        Category category = new Category();
        category.setId(dto.getCategoryId());
        product.setCategory(category);

        return ResponseEntity.ok(productService.create(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(
            @PathVariable Long id,
            @RequestBody ProductDTO dto) {

        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setStockQuantity(dto.getStockQuantity());
        product.setDescription(dto.getDescription());
        product.setImageUrl(dto.getImageUrl());

        Category category = new Category();
        category.setId(dto.getCategoryId());
        product.setCategory(category);

        return ResponseEntity.ok(productService.update(id, product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}