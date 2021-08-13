package com.example.demo.service;

import com.example.demo.converter.ProductConverter;
import com.example.demo.dao.ProductDao;
import com.example.demo.exceptionhandling.DataBaseException;
import com.example.demo.exceptionhandling.ProductNotFoundException;
import com.example.demo.model.Customer;
import com.example.demo.model.Product;
import com.example.demo.dto.product.ProductDto;
import com.example.demo.repository.ProductRepository;
import javassist.NotFoundException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Service
@Data
@RequiredArgsConstructor
public class ProductService {

    ProductRepository productRepository;

    private final ProductDao productDao;
    private final ProductConverter converter;
    private final CustomerService customerService;

    public ProductDto getProductById(Long id) throws ProductNotFoundException {
        Optional<Product> product;
        product = productDao.findById(id);
        isPresent(product);
        return converter.modelToDTO(product.get());
    }

    public List<ProductDto> getAllProducts() throws DataBaseException {
        List<Product> products;
        productRepository.findProductsByCustomer(SecurityContextHolder.getContext().getAuthentication().getName());
        try {
            products = productDao.findAll();
        } catch (Exception e) {
            throw new DataBaseException("Data base issue!", INTERNAL_SERVER_ERROR);
        }
        return converter.modelToDTO(products);
    }

    public List<ProductDto> getAllProductsForUser(Long id) throws DataBaseException {
        List<Product> products;
        try {
            products = productRepository.findProductsByCustomer(customerService.FindUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        } catch (Exception e) {
            throw new DataBaseException("Data base issue!", INTERNAL_SERVER_ERROR);
        }
        return converter.modelToDTO(products);
    }

    public void addProduct(ProductDto productDto) {
        validateProduct(productDto);
        Product product = converter.dtoToModel(productDto);
        product.setCustomer(customerService.FindUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        productDao.save(product);
    }

    public void updateProduct(Long id, ProductDto productDto) throws ProductNotFoundException {
        Optional<Product> receivedProduct = productDao.findById(id);
        isPresent(receivedProduct);
        validateProduct(productDto);

        Product toBeUpdated = receivedProduct.get();
        Customer customer = customerService.FindUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if (toBeUpdated.getCustomer().getId() == customer.getId()) {
            toBeUpdated.setTitle(productDto.getTitle());
            toBeUpdated.setPrice(productDto.getPrice());
            toBeUpdated.setDescription(productDto.getDescription());
        } else
            throw new ProductNotFoundException("Product not found!", INTERNAL_SERVER_ERROR);
        productDao.save(toBeUpdated);
    }

    public void deleteProduct(Long id) throws DataBaseException, ProductNotFoundException {
        Optional<Product> product = productDao.findById(id);
        isPresent(product);
        Product toBeDelete = product.get();
        Customer customer = customerService.FindUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if (toBeDelete.getCustomer().getId() == customer.getId()) {
            productDao.deleteById(id);
        } else
            throw new DataBaseException("Data Source issue, could not delete product", INTERNAL_SERVER_ERROR);
    }


    private void isPresent(Optional<Product> optionalProduct) throws ProductNotFoundException {
        if (optionalProduct.isPresent()) {
            return;
        }
        throw new ProductNotFoundException("Product not found!", INTERNAL_SERVER_ERROR);
    }

    private void validateProduct(ProductDto productDto) throws IllegalArgumentException {
        validateString(productDto.getTitle(), "You are trying to set invalid value for product name!");
        validateString(productDto.getDescription(), "You are trying to set invalid value for product genre!");
    }

    private void validateString(String string, String errorMessage) throws IllegalArgumentException {
        if (string.isEmpty() || string.contains(" ") || string.length() <= 1 || string.matches(".*\\d.*"))
            throw new IllegalArgumentException(errorMessage);
    }

    public Page<Product> getProducts(Pageable page) {
        return productRepository.findAll(page);
    }

}
