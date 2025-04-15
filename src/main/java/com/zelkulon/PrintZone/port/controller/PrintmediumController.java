package com.zelkulon.PrintZone.port.controller;

import com.zelkulon.PrintZone.core.domain.model.Printmedium;
import com.zelkulon.PrintZone.core.domain.service.PrintmediumService;
import com.zelkulon.PrintZone.port.controller.dto.PrintmediumDto;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/printmedien")
public class PrintmediumController {
    private final PrintmediumService printmediumService;

    @Autowired
    public PrintmediumController(PrintmediumService printmediumService) {
        this.printmediumService = printmediumService;
    }

    @GetMapping
    public List<PrintmediumDto> getAllPrintmedien() {
        return printmediumService.findAll().stream()
                .map(p -> new PrintmediumDto(p.getId(), p.getTitel(),p.getArt(), p.getPreis()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PrintmediumDto getPrintmediumById(@PathVariable Long id) {
        Printmedium pm = printmediumService.findById(id);
        return new PrintmediumDto(pm.getId(), pm.getTitel(),pm.getArt(), pm.getPreis());
    }

    @PostMapping
    public PrintmediumDto createPrintmedium(@Valid @RequestBody PrintmediumDto printDto) {
        Printmedium saved = printmediumService.create(printDto.getTitel(),printDto.getArt(), printDto.getPreis());
        return new PrintmediumDto(saved.getId(), saved.getTitel(),saved.getArt(), saved.getPreis());
    }

    @PutMapping("/{id}")
    public PrintmediumDto updatePrintmedium(@PathVariable Long id, @Valid @RequestBody PrintmediumDto printDto) {
        Printmedium updated = printmediumService.update(id, printDto.getTitel(), printDto.getPreis());
        return new PrintmediumDto(updated.getId(), updated.getTitel(),updated.getArt(), updated.getPreis());
    }

    @DeleteMapping("/{id}")
    public void deletePrintmedium(@PathVariable Long id) {
        printmediumService.delete(id);
    }
}
