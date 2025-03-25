package com.micro.servicec;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service-c")
public class ServiceCController {

    @GetMapping("/ping")
    public String ping() {
        return "Service C is up!";
    }
}

