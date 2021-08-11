package com.example.demo;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.apache.catalina.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.stream.IntStream;


@SpringBootApplication
@OpenAPIDefinition
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
     CommandLineRunner run(ProductRepository productRepository) {
        return args -> IntStream.rangeClosed(1, 30).forEach(i -> {
            Product product = new Product();
            product.setDescription("description" + i);
            product.setTitle("title" + i);
            productRepository.save(product);
        });
    }
}