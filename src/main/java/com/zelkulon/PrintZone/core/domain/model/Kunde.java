package com.zelkulon.PrintZone.core.domain.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "kunde")
public class Kunde {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "kunde", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Bestellung> bestellungen;

    @OneToMany(mappedBy = "kunde", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Lieferadresse> lieferadressen;

    public Kunde() { }

    public Kunde(String name) {
        this.name = name;
    }

    // Getters and setters...
    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Bestellung> getBestellungen() { return bestellungen; }
    public void setBestellungen(List<Bestellung> bestellungen) { this.bestellungen = bestellungen; }
    public List<Lieferadresse> getLieferadressen() { return lieferadressen; }
    public void setLieferadressen(List<Lieferadresse> lieferadressen) { this.lieferadressen = lieferadressen; }
}