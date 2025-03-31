package com.srikanth.inventoryManagement.controller;

import com.srikanth.inventoryManagement.model.InventoryItem;
import com.srikanth.inventoryManagement.service.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<InventoryItem> getAll() {
        return service.getAll();
    }

    @GetMapping("/product/{code}")
    public InventoryItem getByProductCode(@PathVariable String code) {
        return service.getByProductCode(code).orElse(null);
    }

    @PostMapping
    public InventoryItem addOrUpdate(@RequestBody InventoryItem item) {
        return service.save(item);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
