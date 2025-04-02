package com.zelkulon.PrintZone.port.controller.dto;

public class BestellpositionDto {
    private Long id;
    private Long bestellungId;
    private Long printmediumId;
    private String printmediumTitel;
    private int menge;

    public BestellpositionDto() { }

    public BestellpositionDto(Long id, Long bestellungId, Long printmediumId, String printmediumTitel, int menge) {
        this.id = id;
        this.bestellungId = bestellungId;
        this.printmediumId = printmediumId;
        this.printmediumTitel = printmediumTitel;
        this.menge = menge;
    }

    // Getters and setters...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getBestellungId() { return bestellungId; }
    public void setBestellungId(Long bestellungId) { this.bestellungId = bestellungId; }
    public Long getPrintmediumId() { return printmediumId; }
    public void setPrintmediumId(Long printmediumId) { this.printmediumId = printmediumId; }
    public String getPrintmediumTitel() { return printmediumTitel; }
    public void setPrintmediumTitel(String printmediumTitel) { this.printmediumTitel = printmediumTitel; }
    public int getMenge() { return menge; }
    public void setMenge(int menge) { this.menge = menge; }
}
