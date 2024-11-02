package com.example.booking.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

@RestController
@RequestMapping("/client")
public class ClientController {

    // Пример для просмотра мероприятий и бронирования билетов

    @GetMapping("/tickets")
    public String showTicketsPage(Model model) {
        // Логика для отображения списка билетов
        return "client/tickets";
    }
}
