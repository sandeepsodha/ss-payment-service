package com.ss.paymentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class PaymentServiceApplication {

	public static void main(String[] args)
	{
		new SpringApplicationBuilder()
				.profiles("dev")
				.sources(PaymentServiceApplication.class)
				.run(args);
	}
}
