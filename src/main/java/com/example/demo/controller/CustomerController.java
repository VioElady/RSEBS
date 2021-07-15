package com.example.demo.controller;

import com.example.demo.dto.customer.CustomerRequestDto;
import com.example.demo.dto.customer.CustomerResponseDto;
import com.example.demo.dto.customer.CustomerSigInDto;
import com.example.demo.service.AuthService;
import com.example.demo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class CustomerController {
    private final AuthService authService;
    private final CustomerService customerService;


    @PostMapping("/sign-in")
    public ResponseEntity<Object> signIn(@RequestBody CustomerSigInDto customerSignIntDto) {
        authService.signIn(customerSignIntDto);
        return ResponseEntity
                .ok()
                .body(HttpStatus.OK);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<CustomerResponseDto> signUp(@RequestBody CustomerRequestDto customerRequestDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(customerService.
                        save(customerRequestDto));
    }
}





















//{
//        "id": 2,
//        "customer": {
//        "id": 2,
//        "username": "ella",
//        "email": "ellamark",
//        "password": "$2a$12$7wzBLh2osRNtZ.NC9b3aae58vVgutSYCj89SNJEELonGS0UNG6fUa"
//        },
//        "title": "chess",
//        "description": "bad",
//        "price": 45.0
//        }