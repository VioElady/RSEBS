package com.example.demo.controller;

import com.example.demo.exceptionhandling.ProductNotFoundException;
import com.example.demo.service.AssessmentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@org.springframework.web.bind.annotation.RestController
@RequestMapping("")

public class AssessmentsController {

    private final AssessmentsService assessmentsService;

    @PostMapping("/likes/{productId}")
    public ResponseEntity<Object> addLikes(@PathVariable Long productId) throws ProductNotFoundException {
        assessmentsService.addAssessment(productId, true);
        return new ResponseEntity<>("Likes true has been added successfully!", HttpStatus.CREATED);
    }

    @PostMapping("/unlikes/{productId}")
    public ResponseEntity<Object> removeLikes(@PathVariable Long productId) throws ProductNotFoundException {
        assessmentsService.addAssessment(productId, false);
        return new ResponseEntity<>("UnLikes true has been added successfully!", HttpStatus.CREATED);
    }
}
