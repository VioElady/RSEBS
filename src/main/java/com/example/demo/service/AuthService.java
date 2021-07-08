package com.example.demo.service;

import com.example.demo.dto.CustomerSigInDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;

    public void signIn(CustomerSigInDto customerSigInDto) {
        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(customerSigInDto.getUsername(), customerSigInDto.getPassword());
        Authentication auth = authenticationManager.authenticate(authReq);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);
    }
}
