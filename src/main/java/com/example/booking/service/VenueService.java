package com.example.booking.service;

import com.example.booking.model.Venue;
import com.example.booking.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenueService {

    @Autowired
    private VenueRepository venueRepository;

    public List<Venue> findAll() {
        return venueRepository.findAll();
    }

    public Venue findById(Long id) {
        return venueRepository.findById(id).orElseThrow(() -> new RuntimeException("Venue not found"));
    }

    public Venue save(Venue venue) {
        return venueRepository.save(venue);
    }

    public Venue update(Long id, Venue updatedVenue) {
        Venue existingVenue = findById(id);
        existingVenue.setName(updatedVenue.getName());
        existingVenue.setLocation(updatedVenue.getLocation());
        return venueRepository.save(existingVenue);
    }

    public void delete(Long id) {
        venueRepository.deleteById(id);
    }
}
