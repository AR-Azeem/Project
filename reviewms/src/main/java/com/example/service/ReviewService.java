package com.example.service;


import com.example.model.Review;
import com.example.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;


    public List<Review> getAllReviews(Long companyId){
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    public Review getReviewsById(long reviewId){
        return reviewRepository.findById(reviewId).orElse(null);
    }

    public void createReview(Review review){
        reviewRepository.save(review);
    }
}
