package com.srikanth.inventoryManagement.service;

import com.srikanth.inventoryManagement.model.InventoryItem;
import com.srikanth.inventoryManagement.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    private final InventoryRepository repository;

    public InventoryService(InventoryRepository repository) {
        this.repository = repository;
    }

    public List<InventoryItem> getAll() {
        return repository.findAll();
    }

    public Optional<InventoryItem> getByProductCode(String productCode) {
        return repository.findByProductCode(productCode);
    }

    public InventoryItem save(InventoryItem item) {
        return repository.save(item);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
