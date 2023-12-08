package ru.vlsu.storage_kurs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vlsu.storage_kurs.entity.Product;
import ru.vlsu.storage_kurs.repo.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public void updateProduct(Long id, Product updatedProduct) {
        Product existingProduct = getProductById(id);
        if (existingProduct != null) {
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setDescription(updatedProduct.getDescription());
            productRepository.save(existingProduct);
        }
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}

