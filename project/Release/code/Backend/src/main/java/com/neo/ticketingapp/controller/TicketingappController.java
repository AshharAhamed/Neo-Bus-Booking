package com.neo.ticketingapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
class TicketingAppController {

    @GetMapping(path = "/test")
    public ResponseEntity<String> getLineCount() {

        return (new ResponseEntity<String>("Working", HttpStatus.OK));
    }

}
