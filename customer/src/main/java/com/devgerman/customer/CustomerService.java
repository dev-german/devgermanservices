package com.devgerman.customer;

import com.devgerman.clients.fraud.FraudCheckResponse;
import com.devgerman.clients.fraud.FraudClient;
import com.devgerman.clients.notification.NotificationClient;
import com.devgerman.clients.notification.NotificationRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;

    public CustomerService(CustomerRepository customerRepository, FraudClient fraudClient, NotificationClient notificationClient) {
        this.customerRepository = customerRepository;
        this.fraudClient = fraudClient;
        this.notificationClient = notificationClient;
    }

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        //todo: check if email valid
        //todo: check if email not taken
        customerRepository.saveAndFlush(customer);
        //todo: check if fraudster

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if(fraudCheckResponse.isFraudster()){
            throw new IllegalStateException("fraudster");
        }
        //todo: send notification
        notificationClient.send(new NotificationRequest(
                "{%s}, welcome to the system".formatted(customer.getFirstName()),
                "German",
                customer.getEmail(),
                customer.getId()
        ));
    }
}
