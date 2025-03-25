package com.micro.servicea;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/service-a")
public class ServiceAController {

    private final RestTemplate restTemplate = new RestTemplate();

    // Load URLs from environment variables (set in docker-compose.yml)
    @Value("${SERVICE_B_URL}") // Default if env variable is missing
    private String serviceBUrl;
    

    @Value("${SERVICE_C_URL}")
    private String serviceCUrl;

    @GetMapping("/")
    public String ready() {
        return "Service A is ready!";
    }

    @GetMapping("/ping")
    public String ping() {
        return "Service A is up!";
    }

    @GetMapping("/call-service-b")
    public String callServiceB() {
        return restTemplate.getForObject(serviceBUrl + "/ping", String.class);
    }

    @GetMapping("/call-service-c")
    public String callServiceC() {
        return restTemplate.getForObject(serviceCUrl + "/ping", String.class);
    }
}
