package com.example.booking.service;

import com.example.booking.model.Review;
import com.example.booking.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ConcertService concertService;

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    public Review findById(Long id) {
        return reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Review not found"));
    }

    public Review save(Review review) {
        // Загружаем связанные сущности из базы данных
        review.setUser(userService.findById(review.getUser().getId()));
        review.setConcert(concertService.findById(review.getConcert().getId()));

        return reviewRepository.save(review);
    }

    public Review update(Long id, Review updatedReview) {
        Review existingReview = findById(id);
        existingReview.setUser(updatedReview.getUser());
        existingReview.setConcert(updatedReview.getConcert());
        existingReview.setComment(updatedReview.getComment());
        existingReview.setRating(updatedReview.getRating());
        return reviewRepository.save(existingReview);
    }

    public void delete(Long id) {
        reviewRepository.deleteById(id);
    }
}
