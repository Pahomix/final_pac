package com.example.booking.service;

import com.example.booking.model.Concert;
import com.example.booking.repository.ConcertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ConcertService {

    @Autowired
    private ConcertRepository concertRepository;

    public List<Concert> findAll() {
        return concertRepository.findAll();
    }

    public Concert findById(Long id) {
        return concertRepository.findById(id).orElseThrow(() -> new RuntimeException("Concert not found"));
    }

    public Concert save(Concert concert) {
        // Проверка на пустое имя
        if (concert.getName() == null || concert.getName().isBlank()) {
            throw new IllegalArgumentException("Название концерта не может быть пустым");
        }
        return concertRepository.save(concert);
    }

    public Concert update(Long id, Concert updatedConcert) {
        // Проверка на пустое имя перед обновлением
        if (updatedConcert.getName() == null || updatedConcert.getName().isBlank()) {
            throw new IllegalArgumentException("Название концерта не может быть пустым");
        }

        Concert existingConcert = findById(id);
        existingConcert.setName(updatedConcert.getName());
        existingConcert.setDateTime(updatedConcert.getDateTime());
        existingConcert.setLocation(updatedConcert.getLocation());
        return concertRepository.save(existingConcert);
    }

    @Transactional
    public void delete(Long id) {
        concertRepository.deleteById(id);
    }
}
