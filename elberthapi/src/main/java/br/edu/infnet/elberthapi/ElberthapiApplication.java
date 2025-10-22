package br.edu.infnet.elberthapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ElberthapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElberthapiApplication.class, args);
	}

}
