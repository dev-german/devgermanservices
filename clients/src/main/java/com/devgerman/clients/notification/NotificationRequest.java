package com.devgerman.clients.notification;

public record NotificationRequest(
        String message,
        String sender,
        String customerEmail,
        Integer customerId
) {
}
