package com.srikanth.notificationManagement.controller;

import com.srikanth.notificationManagement.model.Notification;
import com.srikanth.notificationManagement.service.NotificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }

    @GetMapping
    public List<Notification> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Notification send(@RequestBody Notification notification) {
        return service.send(notification);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
