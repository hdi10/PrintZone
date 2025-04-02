package com.zelkulon.PrintZone.core.domain.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "lieferadresse")
public class Lieferadresse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String strasse;
    private String plz;
    private String ort;
    private String land;

    @ManyToOne
    @JoinColumn(name = "kunde_id", nullable = false)
    private Kunde kunde;

    @OneToMany(mappedBy = "lieferadresse", fetch = FetchType.LAZY)
    private List<Bestellung> bestellungen;  // Bestellungen, die an diese Lieferadresse gehen

    public Lieferadresse() { }

    public Lieferadresse(String strasse, String plz, String ort, String land, Kunde kunde) {
        this.strasse = strasse;
        this.plz = plz;
        this.ort = ort;
        this.land = land;
        this.kunde = kunde;
    }

    // Getters and setters...
    public Long getId() { return id; }
    public String getStrasse() { return strasse; }
    public void setStrasse(String strasse) { this.strasse = strasse; }
    public String getPlz() { return plz; }
    public void setPlz(String plz) { this.plz = plz; }
    public String getOrt() { return ort; }
    public void setOrt(String ort) { this.ort = ort; }
    public String getLand() { return land; }
    public void setLand(String land) { this.land = land; }
    public Kunde getKunde() { return kunde; }
    public void setKunde(Kunde kunde) { this.kunde = kunde; }
    public List<Bestellung> getBestellungen() { return bestellungen; }
    public void setBestellungen(List<Bestellung> bestellungen) { this.bestellungen = bestellungen; }
}