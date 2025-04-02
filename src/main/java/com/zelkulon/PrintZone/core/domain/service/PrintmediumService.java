package com.zelkulon.PrintZone.core.domain.service;

import com.zelkulon.PrintZone.core.domain.model.Printmedium;
import com.zelkulon.PrintZone.port.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.util.List;

@Service
public class PrintmediumService {
    private final PrintmediumRepository printmediumRepository;

    public PrintmediumService(PrintmediumRepository printmediumRepository) {
        this.printmediumRepository = printmediumRepository;
    }

    @Transactional(readOnly = true)
    public List<Printmedium> findAll() {
        return printmediumRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Printmedium findById(Long id) {
        return printmediumRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Printmedium mit ID " + id + " nicht gefunden"));
    }

    @Transactional
    public Printmedium create(String titel, BigDecimal preis) {
        Printmedium pm = new Printmedium(titel, preis);
        return printmediumRepository.save(pm);
    }

    @Transactional
    public Printmedium update(Long id, String titel, BigDecimal preis) {
        Printmedium existing = printmediumRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Printmedium mit ID " + id + " nicht gefunden"));
        existing.setTitel(titel);
        existing.setPreis(preis);
        return printmediumRepository.save(existing);
    }

    @Transactional
    public void delete(Long id) {
        if (!printmediumRepository.existsById(id)) {
            throw new EntityNotFoundException("Printmedium mit ID " + id + " nicht gefunden");
        }
        printmediumRepository.deleteById(id);
    }
}
