package com.srikanth.notificationManagement.repository;

import com.srikanth.notificationManagement.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
