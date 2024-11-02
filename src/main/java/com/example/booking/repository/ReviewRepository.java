package com.example.booking.repository;

import com.example.booking.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByConcertId(Long concertId);
}
