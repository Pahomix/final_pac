package com.example.booking.controller;

import com.example.booking.model.Performer;
import com.example.booking.service.PerformerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/performers")
public class PerformerController {

    @Autowired
    private PerformerService performerService;

    @GetMapping
    public List<Performer> getAllPerformers() {
        return performerService.findAll();
    }

    @GetMapping("/{id}")
    public Performer getPerformerById(@PathVariable Long id) {
        return performerService.findById(id);
    }

    @PostMapping
    public Performer createPerformer(@RequestBody Performer performer) {
        return performerService.save(performer);
    }

    @PutMapping("/{id}")
    public Performer updatePerformer(@PathVariable Long id, @RequestBody Performer performer) {
        return performerService.update(id, performer);
    }

    @DeleteMapping("/{id}")
    public void deletePerformer(@PathVariable Long id) {
        performerService.delete(id);
    }
}
