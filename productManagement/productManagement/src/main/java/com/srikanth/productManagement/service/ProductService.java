package com.srikanth.productManagement.service;

import com.srikanth.productManagement.model.Product;
import com.srikanth.productManagement.repository.ProductRepository;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Cacheable(value = "products")
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    @Cacheable(value = "product", key = "#id")
    public Product getProductById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @CacheEvict(value = {"products"}, allEntries = true)
    public Product createProduct(Product product) {
        return repository.save(product);
    }

    @CachePut(value = "product", key = "#id")
    @CacheEvict(value = {"products"}, allEntries = true)
    public Product updateProduct(Long id, Product updatedProduct) {
        Optional<Product> optionalProduct = repository.findById(id);
        if (optionalProduct.isPresent()) {
            Product existing = optionalProduct.get();
            existing.setName(updatedProduct.getName());
            existing.setQuantity(updatedProduct.getQuantity());
            existing.setPrice(updatedProduct.getPrice());
            return repository.save(existing);
        }
        return null;
    }

    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }
}
