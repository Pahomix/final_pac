package com.example.booking.service;

import com.example.booking.model.Performer;
import com.example.booking.repository.PerformerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerformerService {

    @Autowired
    private PerformerRepository performerRepository;

    public List<Performer> findAll() {
        return performerRepository.findAll();
    }

    public Performer findById(Long id) {
        return performerRepository.findById(id).orElseThrow(() -> new RuntimeException("Performer not found"));
    }

    public Performer save(Performer performer) {
        return performerRepository.save(performer);
    }

    public Performer update(Long id, Performer updatedPerformer) {
        Performer existingPerformer = findById(id);
        existingPerformer.setName(updatedPerformer.getName());
        existingPerformer.setGenre(updatedPerformer.getGenre());
        return performerRepository.save(existingPerformer);
    }

    public void delete(Long id) {
        performerRepository.deleteById(id);
    }
}
