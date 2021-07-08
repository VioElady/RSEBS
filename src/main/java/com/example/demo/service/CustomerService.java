package com.example.demo.service;

import com.example.demo.dto.CustomerRequestDto;
import com.example.demo.dto.CustomerResponseDto;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerResponseDto save(CustomerRequestDto customerRequestDto) {
        customerRepository.findByEmail(customerRequestDto.getEmail()).ifPresent(s -> {
            throw new IllegalArgumentException("Email " + customerRequestDto.getEmail() + " already exists");
        });

        Customer customer = Customer.builder()
                .email(customerRequestDto.getEmail())
                .username(customerRequestDto.getUsername())
                .build();

        customer.setPassword(new BCryptPasswordEncoder(12).encode(customerRequestDto.getPassword()));
        Customer customerResponseDto = customerRepository.save(customer);

        return CustomerResponseDto.builder()
                .id(customerResponseDto.getId())
                .email(customerResponseDto.getEmail())
                .username(customerResponseDto.getUsername())
                .build();
    }
}
