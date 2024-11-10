package com.devgerman.clients.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "notification")
public interface NotificationClient {

    @PostMapping(path = "api/v1/notifications")
    void send(NotificationRequest notificationRequest);
}
