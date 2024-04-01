package com.micro.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@SpringBootApplication
public class MicroServiceApplication {

	private static final String BASEURL = "https://api.openweathermap.org/data/2.5/weather";
	private static final String API_KEY = "488af055c3c272eb71a439355479a400";

	private static final Log logger = LogFactory.getLog(MicroServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MicroServiceApplication.class, args);
	}

	@RequestMapping("/")
	String home() {
		logger.info("home() has been called");
		return "Hello Ericsson!";
	}

	private final RestTemplate restTemplate = new RestTemplate();

	@GetMapping(value = "/weather/{cityName}", produces = "application/json")
	@ResponseBody
	public String getWeatherInfo(@PathVariable String cityName) {
		String url = BASEURL + "?q=" + cityName + "&appid=" + API_KEY;
		return restTemplate.getForObject(url, String.class);
	}
}
