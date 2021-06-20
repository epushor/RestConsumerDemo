package com.example.consumingrest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ConsumingRestApplication {

	private static Logger logger = LoggerFactory.getLogger(ConsumingRestApplication.class);


	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	@Async
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				for (int i = 0; i < 1000; i++) {
					Quote quote = restTemplate.getForObject(
							"https://quoters.apps.pcfone.io/api/random", Quote.class);
					logger.info(quote.toString());
				}
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ConsumingRestApplication.class, args);
	}

}
