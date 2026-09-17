package Web_Drink_Store.webstore.repository;

import Web_Drink_Store.webstore.entity.Product;
import Web_Drink_Store.webstore.enums.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByStatusAndIsDeletedFalse(ProductStatus status);

    List<Product> findByCategoryIdAndStatusAndIsDeletedFalse(
            Long categoryId,
            ProductStatus status
    );

    List<Product> findByNameContainingIgnoreCaseAndStatusAndIsDeletedFalse(
            String name,
            ProductStatus status
    );
}