package com.zelkulon.PrintZone.core.domain.service;


import com.zelkulon.PrintZone.core.domain.model.Kunde;
import com.zelkulon.PrintZone.port.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class KundeService {
    private final KundeRepository kundeRepository;

    public KundeService(KundeRepository kundeRepository) {
        this.kundeRepository = kundeRepository;
    }

    @Transactional(readOnly = true)
    public List<Kunde> findAll() {
        return kundeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Kunde findById(Long id) {
        return kundeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Kunde mit ID " + id + " nicht gefunden"));
    }

    @Transactional
    public Kunde create(Kunde kunde) {
        return kundeRepository.save(kunde);
    }

    @Transactional
    public Kunde update(Long id, String name) {
        Kunde existing = kundeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Kunde mit ID " + id + " nicht gefunden"));
        existing.setName(name);
        return kundeRepository.save(existing);
    }

    @Transactional
    public void delete(Long id) {
        if (!kundeRepository.existsById(id)) {
            throw new EntityNotFoundException("Kunde mit ID " + id + " nicht gefunden");
        }
        kundeRepository.deleteById(id);
    }
}