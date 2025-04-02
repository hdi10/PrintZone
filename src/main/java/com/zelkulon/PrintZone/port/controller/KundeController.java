package com.zelkulon.PrintZone.port.controller;



import com.zelkulon.PrintZone.core.domain.model.Kunde;
import com.zelkulon.PrintZone.core.domain.service.KundeService;
import com.zelkulon.PrintZone.port.controller.dto.KundeDto;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/kunden")
public class KundeController {
    private final KundeService kundeService;

    @Autowired
    public KundeController(KundeService kundeService) {
        this.kundeService = kundeService;
    }

    @GetMapping
    public List<KundeDto> getAllKunden() {
        return kundeService.findAll().stream()
                .map(k -> new KundeDto(k.getId(), k.getName()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public KundeDto getKundeById(@PathVariable Long id) {
        Kunde kunde = kundeService.findById(id);
        return new KundeDto(kunde.getId(), kunde.getName());
    }

    @PostMapping
    public KundeDto createKunde(@Valid @RequestBody KundeDto kundeDto) {
        // ID wird bei Neuanlage ignoriert
        Kunde saved = kundeService.create(new Kunde(kundeDto.getName()));
        return new KundeDto(saved.getId(), saved.getName());
    }

    @PutMapping("/{id}")
    public KundeDto updateKunde(@PathVariable Long id, @Valid @RequestBody KundeDto kundeDto) {
        Kunde updated = kundeService.update(id, kundeDto.getName());
        return new KundeDto(updated.getId(), updated.getName());
    }

    @DeleteMapping("/{id}")
    public void deleteKunde(@PathVariable Long id) {
        kundeService.delete(id);
    }
}