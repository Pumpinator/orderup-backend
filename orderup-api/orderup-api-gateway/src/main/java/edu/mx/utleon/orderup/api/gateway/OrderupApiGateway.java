package edu.mx.utleon.orderup.api.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderupApiGateway {

	public static void main(String[] args) {
		SpringApplication.run(OrderupApiGateway.class, args);
	}

}
