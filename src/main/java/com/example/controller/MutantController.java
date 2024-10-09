package com.example.controller;

import com.example.dto.DnaRequest;
import com.example.service.MutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/mutant")
public class MutantController {

    @Autowired
    private MutantService mutantService;

    @PostMapping("/")
    public ResponseEntity<String> detectMutant(@RequestBody DnaRequest dnaRequest) {
        boolean isMutant = mutantService.isMutant(dnaRequest.getDna());

        if (isMutant) {
            return ResponseEntity.ok("Mutant detected");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not a mutant");
        }
    }
}