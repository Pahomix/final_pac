package com.example.booking.service;

import com.example.booking.model.Ticket;
import com.example.booking.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    public Ticket findById(Long id) {
        return ticketRepository.findById(id).orElseThrow(() -> new RuntimeException("Ticket not found"));
    }

    public Ticket save(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public Ticket update(Long id, Ticket updatedTicket) {
        Ticket existingTicket = findById(id);
        existingTicket.setConcert(updatedTicket.getConcert());
        existingTicket.setUser(updatedTicket.getUser());
        existingTicket.setPrice(updatedTicket.getPrice());
        existingTicket.setIsAvailable(updatedTicket.getIsAvailable());
        return ticketRepository.save(existingTicket);
    }

    public void delete(Long id) {
        ticketRepository.deleteById(id);
    }
}
