package com.zelkulon.PrintZone.core.domain.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "printmedium")
public class Printmedium {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titel;
    private BigDecimal preis;

    @OneToMany(mappedBy = "printmedium", fetch = FetchType.LAZY)
    private List<Bestellposition> bestellpositionen;  // Referenzen auf Bestellpositionen

    public Printmedium() { }

    public Printmedium(String titel, BigDecimal preis) {
        this.titel = titel;
        this.preis = preis;
    }


    // Getters and setters...
    public Long getId() { return id; }

    public void setId(Long id) {
        this.id = id;
    }


    public String getTitel() { return titel; }
    public void setTitel(String titel) { this.titel = titel; }
    public BigDecimal getPreis() { return preis; }
    public void setPreis(BigDecimal preis) { this.preis = preis; }
    public List<Bestellposition> getBestellpositionen() { return bestellpositionen; }
    public void setBestellpositionen(List<Bestellposition> bestellpositionen) { this.bestellpositionen = bestellpositionen; }
}