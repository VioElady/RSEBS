package com.example.demo.repository;

import com.example.demo.model.Assessments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssessmentsRepository extends JpaRepository<Assessments, Long> {
}
