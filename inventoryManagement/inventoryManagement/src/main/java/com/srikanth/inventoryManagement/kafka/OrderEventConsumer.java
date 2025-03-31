package com.srikanth.inventoryManagement.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.srikanth.inventoryManagement.model.InventoryItem;
import com.srikanth.inventoryManagement.repository.InventoryRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderEventConsumer {

    private final InventoryRepository repository;
    private final ObjectMapper objectMapper;

    public OrderEventConsumer(InventoryRepository repository) {
        this.repository = repository;
        this.objectMapper = new ObjectMapper();
    }
    

    // Listen to messages from the "order-events" topic
    @KafkaListener(topics = "order-events", groupId = "inventory-group")
    public void consumeOrderEvent(ConsumerRecord<String, String> record) {
        String message = record.value();
        System.out.println("📥 Inventory received order event: " + message);

        try {
            // Parse the JSON string to a basic Order object
            OrderEvent order = objectMapper.readValue(message, OrderEvent.class);

            // Find inventory item by product name or code
            Optional<InventoryItem> optionalItem = repository.findByProductCode(order.getProductName());

            if (optionalItem.isPresent()) {
                InventoryItem item = optionalItem.get();

                // Reduce stock quantity based on ordered quantity
                item.setQuantity(item.getQuantity() - order.getQuantity());

                // Save updated inventory item
                repository.save(item);

                System.out.println("✅ Inventory updated for product: " + item.getProductCode());
            } else {
                System.err.println("⚠️ Inventory not found for product: " + order.getProductName());
            }

        } catch (Exception e) {
            System.err.println("❌ Error processing order event: " + e.getMessage());
        }
    }
}
