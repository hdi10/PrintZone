package com.zelkulon.PrintZone.port.controller.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class AddBestellpositionDto {
    @NotNull(message = "Bestellung-ID ist erforderlich")
    private Long bestellungId;
    @NotNull(message = "Printmedium-ID ist erforderlich")
    private Long printmediumId;
    @Min(value = 1, message = "Menge muss mindestens 1 sein")
    private int menge;

    public AddBestellpositionDto() { }

    public AddBestellpositionDto(Long bestellungId, Long printmediumId, int menge) {
        this.bestellungId = bestellungId;
        this.printmediumId = printmediumId;
        this.menge = menge;
    }

    // Getters and setters...
    public Long getBestellungId() { return bestellungId; }
    public void setBestellungId(Long bestellungId) { this.bestellungId = bestellungId; }
    public Long getPrintmediumId() { return printmediumId; }
    public void setPrintmediumId(Long printmediumId) { this.printmediumId = printmediumId; }
    public int getMenge() { return menge; }
    public void setMenge(int menge) { this.menge = menge; }
}