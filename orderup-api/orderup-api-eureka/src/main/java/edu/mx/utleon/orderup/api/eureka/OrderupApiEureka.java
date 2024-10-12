package edu.mx.utleon.orderup.api.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class OrderupApiEureka {

	public static void main(String[] args) {
		SpringApplication.run(OrderupApiEureka.class, args);
	}

}
