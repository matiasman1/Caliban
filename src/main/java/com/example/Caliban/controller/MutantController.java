package com.example.Caliban.controller;

import com.example.Caliban.MutantService;
import com.example.Caliban.dto.DnaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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