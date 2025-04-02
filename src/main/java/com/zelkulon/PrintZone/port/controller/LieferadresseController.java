package com.zelkulon.PrintZone.port.controller;

import com.zelkulon.PrintZone.core.domain.model.Lieferadresse;
import com.zelkulon.PrintZone.core.domain.service.LieferadresseService;
import com.zelkulon.PrintZone.port.controller.dto.LieferadresseDto;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/lieferadressen")
public class LieferadresseController {
    private final LieferadresseService lieferadresseService;

    @Autowired
    public LieferadresseController(LieferadresseService lieferadresseService) {
        this.lieferadresseService = lieferadresseService;
    }

    @GetMapping
    public List<LieferadresseDto> getAllLieferadressen() {
        return lieferadresseService.findAll().stream()
                .map(a -> new LieferadresseDto(a.getId(), a.getStrasse(), a.getPlz(), a.getOrt(), a.getLand(), a.getKunde().getId()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public LieferadresseDto getLieferadresseById(@PathVariable Long id) {
        Lieferadresse adr = lieferadresseService.findById(id);
        return new LieferadresseDto(adr.getId(), adr.getStrasse(), adr.getPlz(), adr.getOrt(), adr.getLand(), adr.getKunde().getId());
    }

    @PostMapping
    public LieferadresseDto createLieferadresse(@Valid @RequestBody LieferadresseDto adrDto) {
        Lieferadresse saved = lieferadresseService.create(adrDto.getStrasse(), adrDto.getPlz(), adrDto.getOrt(), adrDto.getLand(), adrDto.getKundeId());
        return new LieferadresseDto(saved.getId(), saved.getStrasse(), saved.getPlz(), saved.getOrt(), saved.getLand(), saved.getKunde().getId());
    }

    @PutMapping("/{id}")
    public LieferadresseDto updateLieferadresse(@PathVariable Long id, @Valid @RequestBody LieferadresseDto adrDto) {
        // Änderung der zugeordneten KundeId wird hier nicht unterstützt
        Lieferadresse updated = lieferadresseService.update(id, adrDto.getStrasse(), adrDto.getPlz(), adrDto.getOrt(), adrDto.getLand());
        return new LieferadresseDto(updated.getId(), updated.getStrasse(), updated.getPlz(), updated.getOrt(), updated.getLand(), updated.getKunde().getId());
    }

    @DeleteMapping("/{id}")
    public void deleteLieferadresse(@PathVariable Long id) {
        lieferadresseService.delete(id);
    }
}