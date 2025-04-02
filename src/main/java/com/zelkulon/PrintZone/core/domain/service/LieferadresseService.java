package com.zelkulon.PrintZone.core.domain.service;

import com.zelkulon.PrintZone.core.domain.model.Kunde;
import com.zelkulon.PrintZone.core.domain.model.Lieferadresse;
import com.zelkulon.PrintZone.port.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class LieferadresseService {
    private final LieferadresseRepository lieferadresseRepository;
    private final KundeRepository kundeRepository;

    public LieferadresseService(LieferadresseRepository lieferadresseRepository, KundeRepository kundeRepository) {
        this.lieferadresseRepository = lieferadresseRepository;
        this.kundeRepository = kundeRepository;
    }

    @Transactional(readOnly = true)
    public List<Lieferadresse> findAll() {
        return lieferadresseRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Lieferadresse findById(Long id) {
        return lieferadresseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lieferadresse mit ID " + id + " nicht gefunden"));
    }

    @Transactional
    public Lieferadresse create(String strasse, String plz, String ort, String land, Long kundeId) {
        Kunde kunde = kundeRepository.findById(kundeId)
                .orElseThrow(() -> new EntityNotFoundException("Kunde mit ID " + kundeId + " nicht gefunden"));
        Lieferadresse lieferadresse = new Lieferadresse(strasse, plz, ort, land, kunde);
        return lieferadresseRepository.save(lieferadresse);
    }

    @Transactional
    public Lieferadresse update(Long id, String strasse, String plz, String ort, String land) {
        Lieferadresse existing = lieferadresseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lieferadresse mit ID " + id + " nicht gefunden"));
        // Kunde des Adressobjekts bleibt unverändert (keine Änderung von kunde_id hier)
        existing.setStrasse(strasse);
        existing.setPlz(plz);
        existing.setOrt(ort);
        existing.setLand(land);
        return lieferadresseRepository.save(existing);
    }

    @Transactional
    public void delete(Long id) {
        if (!lieferadresseRepository.existsById(id)) {
            throw new EntityNotFoundException("Lieferadresse mit ID " + id + " nicht gefunden");
        }
        lieferadresseRepository.deleteById(id);
    }
}

