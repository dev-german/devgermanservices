package com.devgerman.notification.rabbitmq;

import com.devgerman.clients.notification.NotificationRequest;
import com.devgerman.notification.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificationConsumer {

    private final NotificationService notificationService;

    public NotificationConsumer(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @RabbitListener(queues = "${rabbitmq.queue.notification}")
    public void consumer(NotificationRequest notificationRequest){
      log.info("Consumed {} from queue", notificationRequest);
      notificationService.send(notificationRequest);
    }
}
