package com.example.demo.service;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("Users doesn't exists"));
        return new User(customer.getUsername(), customer.getPassword(), new ArrayList<>());
    }

    public UserDetails loadPasswordByUsername(String password, String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByUsernameAndPassword(password, username).orElseThrow(() ->
                new UsernameNotFoundException("User not found with this username or password..."));
        return new User(customer.getPassword(), customer.getUsername(), new ArrayList<>());
    }
}
