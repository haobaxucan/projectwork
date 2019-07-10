package com.ecpss.controller;

import com.ecpss.service.TicketSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketController {
    
    @Autowired
    TicketSerivce ticketSerivce;
    
    @GetMapping("/ticket")
    public String getTicket() {
        return ticketSerivce.getTicket();
    }
}
