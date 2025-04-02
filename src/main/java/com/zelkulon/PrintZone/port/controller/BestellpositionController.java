package com.zelkulon.PrintZone.port.controller;

import com.zelkulon.PrintZone.core.domain.model.Bestellposition;
import com.zelkulon.PrintZone.core.domain.service.BestellpositionService;
import com.zelkulon.PrintZone.port.controller.dto.AddBestellpositionDto;
import com.zelkulon.PrintZone.port.controller.dto.BestellpositionDto;
import com.zelkulon.PrintZone.port.controller.dto.UpdateMengeDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bestellpositionen")
public class BestellpositionController {
    private final BestellpositionService bestellpositionService;

    @Autowired
    public BestellpositionController(BestellpositionService bestellpositionService) {
        this.bestellpositionService = bestellpositionService;
    }

    @GetMapping
    public List<BestellpositionDto> getAllBestellpositionen() {
        return bestellpositionService.findAll().stream()
                .map(pos -> new BestellpositionDto(
                        pos.getId(),
                        pos.getBestellung().getId(),
                        pos.getPrintmedium().getId(),
                        pos.getPrintmedium().getTitel(),
                        pos.getMenge()
                ))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public BestellpositionDto getBestellpositionById(@PathVariable Long id) {
        Bestellposition pos = bestellpositionService.findById(id);
        return new BestellpositionDto(
                pos.getId(),
                pos.getBestellung().getId(),
                pos.getPrintmedium().getId(),
                pos.getPrintmedium().getTitel(),
                pos.getMenge()
        );
    }

    @PostMapping
    public BestellpositionDto addBestellposition(@Valid @RequestBody AddBestellpositionDto addDto) {
        Bestellposition savedPos = bestellpositionService.create(addDto.getBestellungId(), addDto.getPrintmediumId(), addDto.getMenge());
        return new BestellpositionDto(
                savedPos.getId(),
                savedPos.getBestellung().getId(),
                savedPos.getPrintmedium().getId(),
                savedPos.getPrintmedium().getTitel(),
                savedPos.getMenge()
        );
    }

    @PutMapping("/{id}")
    public BestellpositionDto updateBestellposition(@PathVariable Long id, @Valid @RequestBody UpdateMengeDto mengeDto) {
        Bestellposition updatedPos = bestellpositionService.update(id, mengeDto.getMenge());
        return new BestellpositionDto(
                updatedPos.getId(),
                updatedPos.getBestellung().getId(),
                updatedPos.getPrintmedium().getId(),
                updatedPos.getPrintmedium().getTitel(),
                updatedPos.getMenge()
        );
    }

    @DeleteMapping("/{id}")
    public void deleteBestellposition(@PathVariable Long id) {
        bestellpositionService.delete(id);
    }
}
