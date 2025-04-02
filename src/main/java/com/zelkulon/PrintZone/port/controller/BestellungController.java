package com.zelkulon.PrintZone.port.controller;

import com.zelkulon.PrintZone.core.domain.model.Bestellposition;
import com.zelkulon.PrintZone.core.domain.model.Bestellung;
import com.zelkulon.PrintZone.core.domain.model.Printmedium;
import com.zelkulon.PrintZone.core.domain.service.BestellungService;
import com.zelkulon.PrintZone.port.controller.dto.BestellpositionDto;
import com.zelkulon.PrintZone.port.controller.dto.BestellungDto;
import com.zelkulon.PrintZone.port.controller.dto.CreateBestellungDto;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

@RestController
@RequestMapping("/bestellungen")
public class BestellungController {
    private final BestellungService bestellungService;

    @Autowired
    public BestellungController(BestellungService bestellungService) {
        this.bestellungService = bestellungService;
    }

    @GetMapping
    public List<BestellungDto> getAllBestellungen() {
        return bestellungService.findAll().stream().map(order -> {
            // Baue DTO für jede Bestellung (einschließlich ihrer Positionen)
            List<BestellpositionDto> posDtos = order.getPositionen().stream()
                    .map(pos -> new BestellpositionDto(
                            pos.getId(),
                            order.getId(),
                            pos.getPrintmedium().getId(),
                            pos.getPrintmedium().getTitel(),
                            pos.getMenge()
                    ))
                    .collect(Collectors.toList());
            return new BestellungDto(order.getId(), order.getBestelldatum(),
                    order.getKunde().getId(), order.getLieferadresse().getId(), posDtos);
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public BestellungDto getBestellungById(@PathVariable Long id) {
        Bestellung order = bestellungService.findById(id);
        List<BestellpositionDto> posDtos = order.getPositionen().stream()
                .map(pos -> new BestellpositionDto(
                        pos.getId(),
                        order.getId(),
                        pos.getPrintmedium().getId(),
                        pos.getPrintmedium().getTitel(),
                        pos.getMenge()
                ))
                .collect(Collectors.toList());
        return new BestellungDto(order.getId(), order.getBestelldatum(),
                order.getKunde().getId(), order.getLieferadresse().getId(), posDtos);
    }

    @PostMapping
    public BestellungDto createBestellung(@Valid @RequestBody CreateBestellungDto createDto) {
        // Bestellpositionen aus DTOs zusammenstellen
        List<Bestellposition> positions = new ArrayList<>();
        for (var itemDto : createDto.getPositionen()) {
            Printmedium dummy = new Printmedium();
            dummy.setId(itemDto.getPrintmediumId());
            Bestellposition pos = new Bestellposition();
            pos.setPrintmedium(dummy);
            pos.setMenge(itemDto.getMenge());
            positions.add(pos);
        }
        Bestellung savedOrder = bestellungService.create(createDto.getKundeId(), createDto.getLieferadresseId(), positions);
        List<BestellpositionDto> posDtos = savedOrder.getPositionen().stream()
                .map(pos -> new BestellpositionDto(
                        pos.getId(),
                        savedOrder.getId(),
                        pos.getPrintmedium().getId(),
                        pos.getPrintmedium().getTitel(),
                        pos.getMenge()
                ))
                .collect(Collectors.toList());
        return new BestellungDto(savedOrder.getId(), savedOrder.getBestelldatum(),
                savedOrder.getKunde().getId(), savedOrder.getLieferadresse().getId(), posDtos);
    }

    @PutMapping("/{id}/adresse/{adresseId}")
    public BestellungDto updateLieferadresse(@PathVariable Long id, @PathVariable Long adresseId) {
        Bestellung updatedOrder = bestellungService.updateAddress(id, adresseId);
        // Rückgabe mit aktualisierten Lieferadressdaten (Positionen werden hier weggelassen)
        return new BestellungDto(updatedOrder.getId(), updatedOrder.getBestelldatum(),
                updatedOrder.getKunde().getId(), updatedOrder.getLieferadresse().getId(), null);
    }

    @DeleteMapping("/{id}")
    public void deleteBestellung(@PathVariable Long id) {
        bestellungService.delete(id);
    }
}

