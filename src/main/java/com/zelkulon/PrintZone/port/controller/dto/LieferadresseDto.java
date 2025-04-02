package com.zelkulon.PrintZone.port.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LieferadresseDto {
    private Long id;
    @NotBlank(message = "Straße darf nicht leer sein")
    private String strasse;
    @NotBlank(message = "PLZ darf nicht leer sein")
    private String plz;
    @NotBlank(message = "Ort darf nicht leer sein")
    private String ort;
    @NotBlank(message = "Land darf nicht leer sein")
    private String land;
    @NotNull(message = "Kunde-ID ist erforderlich")
    private Long kundeId;

    public LieferadresseDto() { }

    public LieferadresseDto(Long id, String strasse, String plz, String ort, String land, Long kundeId) {
        this.id = id;
        this.strasse = strasse;
        this.plz = plz;
        this.ort = ort;
        this.land = land;
        this.kundeId = kundeId;
    }

    // Getters and setters...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getStrasse() { return strasse; }
    public void setStrasse(String strasse) { this.strasse = strasse; }
    public String getPlz() { return plz; }
    public void setPlz(String plz) { this.plz = plz; }
    public String getOrt() { return ort; }
    public void setOrt(String ort) { this.ort = ort; }
    public String getLand() { return land; }
    public void setLand(String land) { this.land = land; }
    public Long getKundeId() { return kundeId; }
    public void setKundeId(Long kundeId) { this.kundeId = kundeId; }
}