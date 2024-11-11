package com.devgerman.notification;

import com.devgerman.clients.notification.NotificationRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void send(NotificationRequest notificationRequest) {
        notificationRepository.save(
          Notification.builder()
                  .message(notificationRequest.message())
                  .sender(notificationRequest.sender())
                  .sentAt(LocalDateTime.now())
                  .toCustomerEmail(notificationRequest.customerEmail())
                  .toCustomerId(notificationRequest.customerId())
                  .build()
        );
    }
}
