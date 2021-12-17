package com.jules.controllers;

import com.jules.dtos.Menu;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RestController
@RequestMapping("/drinks")
public class DrinksControllers {

    public static final String BAR_SERVER_ID = "bar-server";

    private final RestTemplate restTemplate;
    private final DiscoveryClient client;


    public DrinksControllers(RestTemplate restTemplate,
                            DiscoveryClient client) {
        this.restTemplate = restTemplate;
        this.client = client;
    }

    @GetMapping("menu")
    public ResponseEntity<Menu> getMenu() {
        ServiceInstance backendInstance = client.getInstances(BAR_SERVER_ID).get(0);
        URI backendUrl = backendInstance.getUri().resolve("/drinks/menu");
        var menu = restTemplate.getForObject(backendUrl, Menu.class);
        return ResponseEntity.ok(menu);
    }
}
