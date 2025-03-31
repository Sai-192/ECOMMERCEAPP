package com.srikanth.notificationManagement.service;

import com.srikanth.notificationManagement.model.Notification;
import com.srikanth.notificationManagement.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository repository;

    public NotificationService(NotificationRepository repository) {
        this.repository = repository;
    }

    public List<Notification> getAll() {
        return repository.findAll();
    }

    public Notification send(Notification notification) {
        // In future: send email/SMS/push here
        return repository.save(notification);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
