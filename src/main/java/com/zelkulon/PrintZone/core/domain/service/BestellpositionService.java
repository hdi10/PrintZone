package com.zelkulon.PrintZone.core.domain.service;

import com.zelkulon.PrintZone.core.domain.model.Bestellposition;
import com.zelkulon.PrintZone.core.domain.model.Bestellung;
import com.zelkulon.PrintZone.core.domain.model.Printmedium;
import com.zelkulon.PrintZone.port.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BestellpositionService {
    private final BestellpositionRepository bestellpositionRepository;
    private final BestellungRepository bestellungRepository;
    private final PrintmediumRepository printmediumRepository;

    public BestellpositionService(BestellpositionRepository bestellpositionRepository,
                                  BestellungRepository bestellungRepository,
                                  PrintmediumRepository printmediumRepository) {
        this.bestellpositionRepository = bestellpositionRepository;
        this.bestellungRepository = bestellungRepository;
        this.printmediumRepository = printmediumRepository;
    }

    @Transactional(readOnly = true)
    public List<Bestellposition> findAll() {
        return bestellpositionRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Bestellposition findById(Long id) {
        return bestellpositionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bestellposition mit ID " + id + " nicht gefunden"));
    }

    @Transactional
    public Bestellposition create(Long bestellungId, Long printmediumId, int menge) {
        Bestellung bestellung = bestellungRepository.findById(bestellungId)
                .orElseThrow(() -> new EntityNotFoundException("Bestellung mit ID " + bestellungId + " nicht gefunden"));
        Printmedium printmedium = printmediumRepository.findById(printmediumId)
                .orElseThrow(() -> new EntityNotFoundException("Printmedium mit ID " + printmediumId + " nicht gefunden"));
        Bestellposition position = new Bestellposition(bestellung, printmedium, menge);
        return bestellpositionRepository.save(position);
    }

    @Transactional
    public Bestellposition update(Long id, int neueMenge) {
        Bestellposition position = bestellpositionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bestellposition mit ID " + id + " nicht gefunden"));
        position.setMenge(neueMenge);
        return bestellpositionRepository.save(position);
    }

    @Transactional
    public void delete(Long id) {
        if (!bestellpositionRepository.existsById(id)) {
            throw new EntityNotFoundException("Bestellposition mit ID " + id + " nicht gefunden");
        }
        bestellpositionRepository.deleteById(id);
    }
}