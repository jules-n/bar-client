package com.jules.controllers;

import com.jules.dtos.OrderPostDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RestController
@RequestMapping("orders")
public class OrderControllers {

    public static final String BAR_SERVER_ID = "bar-server";
    private final DiscoveryClient client;


    public OrderControllers(DiscoveryClient client) {
        this.client = client;
    }

   @PostMapping("create-order")
    public ResponseEntity<Double> createOrder(@RequestBody OrderPostDTO dto, Authentication authentication) {
        ServiceInstance backendInstance = client.getInstances(BAR_SERVER_ID).get(0);
        URI backendUrl = backendInstance.getUri().resolve("/orders/create-order");
        var username = ((UserDetails)authentication.getPrincipal()).getUsername();
        var password = ((UserDetails)authentication.getPrincipal()).getPassword();
        RestTemplate restTemplate = new RestTemplateBuilder().basicAuthentication(
                username, password
        ).build();
        Double check = restTemplate.getForObject(backendUrl, Double.class);
        return ResponseEntity.ok(check);
    }
}
