package practice.application.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import practice.application.models.entities.ProductEntity;
import practice.application.repositories.ProductRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    private final ProductRepository productRepo;

    @Autowired
    public ProductService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    public List<ProductEntity> getAllProducts() {
        return productRepo.findAll();
    }

    public List<ProductEntity> getAllProducts(String category) {
        return productRepo.findAllByCategory(category);
    }

    public ProductEntity getProductById(UUID id) {
        return productRepo.findById(id)
                          .orElse(null);
    }

    @Transactional
    public ProductEntity saveProduct(ProductEntity product) {
        assert product != null;

        return productRepo.save(product);
    }

    @Transactional
    public void deleteProduct(UUID id) {
        assert id != null;

        productRepo.deleteById(id);
    }

    @Transactional
    public ProductEntity updateProduct(ProductEntity product) {
        assert product != null;
        assert product.getProductId() != null;

        return productRepo.save(product);
    }

    public long countProducts() {
        return productRepo.count();
    }
}

// TODO 문서화 필요
// TODO_imp delete 기능 만들어야 함.