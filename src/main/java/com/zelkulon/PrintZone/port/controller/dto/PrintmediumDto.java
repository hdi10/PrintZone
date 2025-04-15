package com.zelkulon.PrintZone.port.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class PrintmediumDto {
    private Long id;
    @NotBlank(message = "Titel darf nicht leer sein")
    private String titel;
    @NotNull
    private String art;
    @NotNull(message = "Preis ist erforderlich")
    private BigDecimal preis;

    public PrintmediumDto() { }

    public PrintmediumDto(Long id, String titel, String art, BigDecimal preis) {
        this.id = id;
        this.titel = titel;
        this.art = art;
        this.preis = preis;
    }

    // Getters and setters...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitel() { return titel; }
    public void setTitel(String titel) { this.titel = titel; }
    public BigDecimal getPreis() { return preis; }
    public void setPreis(BigDecimal preis) { this.preis = preis; }

    public String getArt() {
        return art;
    }

    public void setArt(String art) {
        this.art = art;
    }
}
