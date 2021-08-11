package com.example.demo.service;

import com.example.demo.converter.ProductConverter;
import com.example.demo.dao.ProductDao;
import com.example.demo.exceptionhandling.ProductNotFoundException;
import com.example.demo.model.Assessments;
import com.example.demo.model.Customer;
import com.example.demo.model.Product;
import com.example.demo.repository.AssessmentsRepository;
import com.example.demo.repository.ProductRepository;
import javassist.NotFoundException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.http.HttpStatus.*;


@Service
@RequiredArgsConstructor
@Data
public class AssessmentsService {

    private final AssessmentsRepository assessmentsRepository;
    private final ProductRepository productRepository;
    private final CustomerService customerService;
    private final ProductDao productDao;
    private ProductConverter converter;

    public void addAssessment(Long productId, Boolean likes) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(productId);
        Customer customer = customerService.FindUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        if (product.isPresent()) {
            if (product.get().getCustomer().getId().equals(customer.getId())) {
                throw new ProductNotFoundException("The product belongs to you, don't appreciate it.", INTERNAL_SERVER_ERROR);
            }
            {
                Assessments assessments = new Assessments();
                assessments.setProduct(product.get());
                assessments.setCustomer(customer);
                assessments.setLikes(likes);
                assessmentsRepository.save(assessments);
            }
        } else
            throw new ProductNotFoundException("Product not found, you entered the wrong product!", INTERNAL_SERVER_ERROR);
    }
}