package com.example.booking.service;

import com.example.booking.exception.ResourceNotFoundException;
import com.example.booking.model.Payment;
import com.example.booking.model.User;
import com.example.booking.repository.PaymentRepository;
import com.example.booking.repository.ReviewRepository;
import com.example.booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    public Payment findById(Long id) {
        return paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Payment update(Long paymentId, Payment updatedPayment) {
        Payment existingPayment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found"));

        if (updatedPayment.getUser() != null && updatedPayment.getUser().getId() != null) {
            User user = userRepository.findById(updatedPayment.getUser().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
            existingPayment.setUser(user);
        }

        existingPayment.setAmount(updatedPayment.getAmount());
        existingPayment.setPaymentDate(updatedPayment.getPaymentDate());

        return paymentRepository.save(existingPayment);
    }




    public void delete(Long id) {
        Payment payment = findById(id);
        User user = payment.getUser();
        if (user != null && !user.getReviews().isEmpty()) {
            reviewRepository.deleteAll(user.getReviews());
        }
        paymentRepository.deleteById(id);
    }


}
