package com.example.Caliban.repository;

import com.example.Caliban.model.DNA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DNARepository extends JpaRepository<DNA, Long> {
    // Custom query to find by DNA sequence if necessary
    boolean existsByDna(String[] dna);
    long countByIsMutant(boolean isMutant);

}