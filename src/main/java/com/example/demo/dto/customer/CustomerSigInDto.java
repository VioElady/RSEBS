package com.example.demo.dto.customer;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSigInDto {
    private String username;
    private String password;
}
