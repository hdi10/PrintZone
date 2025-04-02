/**
 * Bestellung-Service
 * Harun Dastekin 2025
 */
package com.zelkulon.PrintZone.core.domain.service;
import com.zelkulon.PrintZone.core.domain.model.*;
import com.zelkulon.PrintZone.port.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

@Service
public class BestellungService {
    private final BestellungRepository bestellungRepository;
    private final KundeRepository kundeRepository;
    private final LieferadresseRepository lieferadresseRepository;
    private final PrintmediumRepository printmediumRepository;

    public BestellungService(BestellungRepository bestellungRepository,
                             KundeRepository kundeRepository,
                             LieferadresseRepository lieferadresseRepository,
                             PrintmediumRepository printmediumRepository) {
        this.bestellungRepository = bestellungRepository;
        this.kundeRepository = kundeRepository;
        this.lieferadresseRepository = lieferadresseRepository;
        this.printmediumRepository = printmediumRepository;
    }

    @Transactional(readOnly = true)
    public List<Bestellung> findAll() {
        return bestellungRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Bestellung findById(Long id) {
        return bestellungRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bestellung mit ID " + id + " nicht gefunden"));
    }

    @Transactional
    public Bestellung create(Long kundeId, Long lieferadresseId, List<Bestellposition> positionen) {
        // Kunde und Lieferadresse validieren und laden
        Kunde kunde = kundeRepository.findById(kundeId)
                .orElseThrow(() -> new EntityNotFoundException("Kunde mit ID " + kundeId + " nicht gefunden"));
        Lieferadresse lieferadresse = lieferadresseRepository.findById(lieferadresseId)
                .orElseThrow(() -> new EntityNotFoundException("Lieferadresse mit ID " + lieferadresseId + " nicht gefunden"));
        // Optional: Überprüfen, ob die Lieferadresse zum Kunden gehört
        if (!lieferadresse.getKunde().getId().equals(kunde.getId())) {
            throw new IllegalArgumentException("Lieferadresse gehört nicht zum angegebenen Kunden");
        }

        // Bestellung mit Kunde und Lieferadresse anlegen
        Bestellung bestellung = new Bestellung(kunde, lieferadresse);
        bestellung.setPositionen(new ArrayList<>());  // Initialisiere die Bestellpositionen-Liste

        // Bestellpositionen hinzufügen
        for (Bestellposition pos : positionen) {
            Printmedium produkt = printmediumRepository.findById(pos.getPrintmedium().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Printmedium mit ID " + pos.getPrintmedium().getId() + " nicht gefunden"));
            Bestellposition neuePos = new Bestellposition(bestellung, produkt, pos.getMenge());
            bestellung.getPositionen().add(neuePos);
        }

        return bestellungRepository.save(bestellung);
    }

    @Transactional
    public Bestellung updateAddress(Long bestellungId, Long neueLieferadresseId) {
        Bestellung bestellung = bestellungRepository.findById(bestellungId)
                .orElseThrow(() -> new EntityNotFoundException("Bestellung mit ID " + bestellungId + " nicht gefunden"));
        Lieferadresse neueAdresse = lieferadresseRepository.findById(neueLieferadresseId)
                .orElseThrow(() -> new EntityNotFoundException("Lieferadresse mit ID " + neueLieferadresseId + " nicht gefunden"));
        // Optional: Überprüfen, ob die neue Adresse zum gleichen Kunden gehört
        if (!neueAdresse.getKunde().getId().equals(bestellung.getKunde().getId())) {
            throw new IllegalArgumentException("Neue Lieferadresse gehört nicht zum Kunden der Bestellung");
        }
        bestellung.setLieferadresse(neueAdresse);
        return bestellungRepository.save(bestellung);
    }

    @Transactional
    public void delete(Long id) {
        if (!bestellungRepository.existsById(id)) {
            throw new EntityNotFoundException("Bestellung mit ID " + id + " nicht gefunden");
        }
        bestellungRepository.deleteById(id);
    }
}