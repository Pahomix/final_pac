package com.example.booking.controller;

import com.example.booking.model.Concert;
import com.example.booking.repository.ConcertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organizer/concerts")
public class ConcertController {

    @Autowired
    private ConcertRepository concertRepository;

    @GetMapping
    public List<Concert> getAllConcerts() {
        return concertRepository.findAll();
    }

    @GetMapping("/{id}")
    public Concert getConcertById(@PathVariable Long id) {
        return concertRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Concert not found"));
    }

    @PostMapping
    public Concert createConcert(@RequestBody Concert concert) {
        return concertRepository.save(concert);
    }

    @PutMapping("/{id}")
    public Concert updateConcert(@PathVariable Long id, @RequestBody Concert updatedConcert) {
        Concert existingConcert = concertRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Concert not found"));

        existingConcert.setName(updatedConcert.getName());
        existingConcert.setDateTime(updatedConcert.getDateTime());
        existingConcert.setLocation(updatedConcert.getLocation());

        return concertRepository.save(existingConcert);
    }

    @DeleteMapping("/{id}")
    public void deleteConcert(@PathVariable Long id) {
        concertRepository.deleteById(id);
    }
}
