package com.example.service;

import com.example.model.DNA;
import com.example.repository.DNARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.model.MutantDetector;

@Service
public class MutantService {

    private static final int SEQUENCE_LENGTH = 4; // Numero del tamaño de secuencia a encontrar

    @Autowired
    private DNARepository dnaRepository;

    // Method to check if a DNA sequence belongs to a mutant
    public boolean isMutant(String[] dna) {

        MutantDetector detector = new MutantDetector(dna);
        boolean isMutant = detector.isMutant();

        if (!dnaRepository.existsByDna(dna)) {
            // Save DNA sequence to the database
            DNA dnaEntity = new DNA();
            dnaEntity.setDna(dna);
            dnaEntity.setMutant(isMutant);
            dnaRepository.save(dnaEntity);
        }

        return isMutant;
    }

}