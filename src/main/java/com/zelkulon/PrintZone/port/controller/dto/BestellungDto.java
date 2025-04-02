package com.zelkulon.PrintZone.port.controller.dto;

import java.time.LocalDateTime;
import java.util.List;

public class BestellungDto {
    private Long id;
    private LocalDateTime bestelldatum;
    private Long kundeId;
    private Long lieferadresseId;
    private List<BestellpositionDto> positionen;

    public BestellungDto() {}

    public BestellungDto(Long id, LocalDateTime bestelldatum, Long kundeId, Long lieferadresseId, List<BestellpositionDto> positionen) {
        this.id = id;
        this.bestelldatum = bestelldatum;
        this.kundeId = kundeId;
        this.lieferadresseId = lieferadresseId;
        this.positionen = positionen;
    }

    // Getter & Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getBestelldatum() { return bestelldatum; }
    public void setBestelldatum(LocalDateTime bestelldatum) { this.bestelldatum = bestelldatum; }

    public Long getKundeId() { return kundeId; }
    public void setKundeId(Long kundeId) { this.kundeId = kundeId; }

    public Long getLieferadresseId() { return lieferadresseId; }
    public void setLieferadresseId(Long lieferadresseId) { this.lieferadresseId = lieferadresseId; }

    public List<BestellpositionDto> getPositionen() { return positionen; }
    public void setPositionen(List<BestellpositionDto> positionen) { this.positionen = positionen; }
}
