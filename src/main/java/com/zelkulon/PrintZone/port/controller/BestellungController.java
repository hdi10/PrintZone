package com.zelkulon.PrintZone.port.controller;

import com.zelkulon.PrintZone.core.domain.model.Bestellung;
import com.zelkulon.PrintZone.core.domain.service.BestellungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bestellungen")
public class BestellungController {

    @Autowired
    private final BestellungService bestellungService;

    public BestellungController(BestellungService bestellungService) {
        this.bestellungService = bestellungService;
    }

    public ResponseEntity<List<Bestellung>> getAllBestellungen(){
        return ResponseEntity.ok(bestellungService.getAllBestellungen());
    }

}
