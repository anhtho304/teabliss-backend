package Web_Drink_Store.webstore.service;

import Web_Drink_Store.webstore.entity.Product;
import Web_Drink_Store.webstore.enums.ProductStatus;
import Web_Drink_Store.webstore.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll() {
        return productRepository.findByStatusAndIsDeletedFalse(
                ProductStatus.ACTIVE
        );
    }

    public Product getById(Long id) {
        return productRepository.findById(id)
                .filter(product -> !product.isDeleted())
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public List<Product> search(String name) {
        return productRepository.findByNameContainingIgnoreCaseAndStatusAndIsDeletedFalse(
                name,
                ProductStatus.ACTIVE
        );
    }

    public List<Product> getByCategory(Long categoryId) {
        return productRepository.findByCategoryIdAndStatusAndIsDeletedFalse(
                categoryId,
                ProductStatus.ACTIVE
        );
    }

    public Product create(Product product) {
        product.setStatus(ProductStatus.ACTIVE);
        product.setDeleted(false);
        return productRepository.save(product);
    }

    public Product update(Long id, Product newProduct) {
        Product product = productRepository.findById(id)
                .filter(p -> !p.isDeleted())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(newProduct.getName());
        product.setPrice(newProduct.getPrice());
        product.setStockQuantity(newProduct.getStockQuantity());
        product.setDescription(newProduct.getDescription());
        product.setImageUrl(newProduct.getImageUrl());
        product.setCategory(newProduct.getCategory());

        return productRepository.save(product);
    }

    public void delete(Long id) {
        Product product = productRepository.findById(id)
                .filter(p -> !p.isDeleted())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setStatus(ProductStatus.INACTIVE);
        product.setDeleted(true);

        productRepository.save(product);
    }
}