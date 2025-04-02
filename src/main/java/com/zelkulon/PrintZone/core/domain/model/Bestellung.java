package com.zelkulon.PrintZone.core.domain.model;

import jakarta.persistence.*;


import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bestellung")
public class Bestellung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime bestelldatum;

    @ManyToOne
    @JoinColumn(name = "kunde_id", nullable = false)
    private Kunde kunde;

    @ManyToOne
    @JoinColumn(name = "lieferadresse_id", nullable = false)
    private Lieferadresse lieferadresse;

    @OneToMany(mappedBy = "bestellung", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Bestellposition> positionen;

    public Bestellung() {
        this.bestelldatum = LocalDateTime.now();
    }

    public Bestellung(Kunde kunde, Lieferadresse lieferadresse) {
        this.kunde = kunde;
        this.lieferadresse = lieferadresse;
        this.bestelldatum = LocalDateTime.now();
    }

    // Getters and setters...
    public Long getId() { return id; }
    public LocalDateTime getBestelldatum() { return bestelldatum; }
    public void setBestelldatum(LocalDateTime bestelldatum) { this.bestelldatum = bestelldatum; }
    public Kunde getKunde() { return kunde; }
    public void setKunde(Kunde kunde) { this.kunde = kunde; }
    public Lieferadresse getLieferadresse() { return lieferadresse; }
    public void setLieferadresse(Lieferadresse lieferadresse) { this.lieferadresse = lieferadresse; }
    public List<Bestellposition> getPositionen() { return positionen; }
    public void setPositionen(List<Bestellposition> positionen) { this.positionen = positionen; }
}
