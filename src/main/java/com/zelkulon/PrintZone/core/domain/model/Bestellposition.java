package com.zelkulon.PrintZone.core.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ist", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"bestellung_id", "printmedium_id"})
})
public class Bestellposition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int menge;

    @ManyToOne
    @JoinColumn(name = "bestellung_id", nullable = false)
    private Bestellung bestellung;

    @ManyToOne
    @JoinColumn(name = "printmedium_id", nullable = false)
    private Printmedium printmedium;

    public Bestellposition() { }

    public Bestellposition(Bestellung bestellung, Printmedium printmedium, int menge) {
        this.bestellung = bestellung;
        this.printmedium = printmedium;
        this.menge = menge;
    }

    // Getters and setters...
    public Long getId() { return id; }
    public int getMenge() { return menge; }
    public void setMenge(int menge) { this.menge = menge; }
    public Bestellung getBestellung() { return bestellung; }
    public void setBestellung(Bestellung bestellung) { this.bestellung = bestellung; }
    public Printmedium getPrintmedium() { return printmedium; }
    public void setPrintmedium(Printmedium printmedium) { this.printmedium = printmedium; }
}
