package com.example.demo.repository;

import com.example.demo.model.AssessmentID;
import com.example.demo.model.Assessments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssessmentsRepository extends JpaRepository<Assessments, AssessmentID> {

    @Query(
            value = "SELECT * FROM assessments WHERE customer_id = 2 AND product_id = 3;", nativeQuery = true)
    Optional<Assessments> findLikeByProductIdAndCustomerId(Long customerId, Long productId);

    @Query(
            value = "UPDATE assessments SET likes = TRUE WHERE likes = FALSE AND customer_id = 2 AND product_id = 3;", nativeQuery = true)
    Optional<Assessments> getLikeByProductIdAndCustomerId(Long customerId, Long productId, Boolean likes);

    @Query(
            value = "INSERT INTO assessments (likes, customer_id, product_id) VALUES (true, 3, 2);", nativeQuery = true)
    Optional<Assessments> getAssessmentsByActiveNotLike(Long customerId, Long productId, Boolean likes);

    @Query(
            value = "UPDATE assessments SET likes = FALSE WHERE likes = TRUE AND customer_id = 2 AND product_id = 3;"
            , nativeQuery = true)
    Optional<Assessments> getDisLikeByProductIdAndCustomerId(Long customerId, Long productId, Boolean likes);

    @Query(
            value = "INSERT INTO assessments(likes, customer_id, product_id) VALUES (false, 2, 2)", nativeQuery = true)
    Optional<Assessments> getDisLikeByActiveNotLike(Long customerId, Long productId, Boolean likes);

}