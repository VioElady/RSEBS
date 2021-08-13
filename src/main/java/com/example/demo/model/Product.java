package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "products")

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

//    @ManyToMany(mappedBy = "assessments")
//    Set<Customer> assessments;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")

    private Customer customer;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;
}
