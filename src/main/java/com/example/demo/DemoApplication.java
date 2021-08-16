package com.example.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication

public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

//    @Bean
//     CommandLineRunner run(ProductRepository productRepository) {
//        return args -> IntStream.rangeClosed(1, 30).forEach(i -> {
//            Product product = new Product();
//            product.setDescription("description" + i);
//            product.setTitle("title" + i);
//            productRepository.save(product);
//        });
//    }
}