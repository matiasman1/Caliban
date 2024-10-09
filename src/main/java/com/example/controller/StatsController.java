package com.example.controller;

import com.example.service.DNAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/stats")
public class StatsController {

    private final DNAService dnaService;

    @Autowired
    public StatsController(DNAService dnaService) {
        this.dnaService = dnaService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getStats() {
        Map<String, Object> stats = dnaService.getStats();
        return new ResponseEntity<>(stats, HttpStatus.OK);
    }
}
