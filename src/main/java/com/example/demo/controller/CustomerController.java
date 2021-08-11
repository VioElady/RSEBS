package com.example.demo.controller;

import com.example.demo.dto.customer.CustomerRequestDto;
import com.example.demo.dto.customer.CustomerResponseDto;
import com.example.demo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/sign-up")
    public ResponseEntity<CustomerResponseDto> signUp(@RequestBody CustomerRequestDto customerRequestDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(customerService.
                        save(customerRequestDto));
    }
}
