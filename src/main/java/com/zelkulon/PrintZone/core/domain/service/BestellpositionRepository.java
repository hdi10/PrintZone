package com.zelkulon.PrintZone.core.domain.service;


import com.zelkulon.PrintZone.core.domain.model.Bestellposition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BestellpositionRepository extends JpaRepository<Bestellposition, Long> {
}