/**
 * Bestellung-Service
 * Harun Dastekin 2025
 */
package com.zelkulon.PrintZone.core.domain.service;

import com.zelkulon.PrintZone.core.domain.model.Bestellung;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BestellungService {
    private final BestellungRepository bestellungRepository;

    public BestellungService(BestellungRepository bestellungRepository) {
        this.bestellungRepository = bestellungRepository;
    }

    public List<Bestellung> getAllBestellungen(){
        return bestellungRepository.getAllBestellungen();
    }
}
