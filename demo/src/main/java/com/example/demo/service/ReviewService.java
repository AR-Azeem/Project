package com.example.demo.service;


import com.example.demo.model.Company;
import com.example.demo.model.Review;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    CompanyService companyService;

    public List<Review> getAllReviews(Long companyId){
        Company company = companyService.getCompaniesById(companyId);
        if(company!=null) {
            return company.getReviews();
        }
        return null;
    }

    public Review getReviewsById(Long companyId,long reviewId){
        Company company = companyService.getCompaniesById(companyId);
        if(null!=company) {
            return company.getReviews().stream()
                    .filter(data-> data.getId().equals(reviewId))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    public void createReview(Long companyId, Review review){
        Company company = companyService.getCompaniesById(companyId);
        if(null!=company) {
            review.setCompany(company);
            reviewRepository.save(review);
        }

    }
}
