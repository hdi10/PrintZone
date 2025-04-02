package com.zelkulon.PrintZone.port.controller.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class CreateBestellungDto {
    @NotNull(message = "Kunde-ID ist erforderlich")
    private Long kundeId;
    @NotNull(message = "Lieferadresse-ID ist erforderlich")
    private Long lieferadresseId;
    @NotEmpty(message = "Bestellpositionen dürfen nicht leer sein")
    @Valid
    private List<CreateBestellpositionDto> positionen;

    public CreateBestellungDto() { }

    public CreateBestellungDto(Long kundeId, Long lieferadresseId, List<CreateBestellpositionDto> positionen) {
        this.kundeId = kundeId;
        this.lieferadresseId = lieferadresseId;
        this.positionen = positionen;
    }

    // Getters and setters...
    public Long getKundeId() { return kundeId; }
    public void setKundeId(Long kundeId) { this.kundeId = kundeId; }
    public Long getLieferadresseId() { return lieferadresseId; }
    public void setLieferadresseId(Long lieferadresseId) { this.lieferadresseId = lieferadresseId; }
    public List<CreateBestellpositionDto> getPositionen() { return positionen; }
    public void setPositionen(List<CreateBestellpositionDto> positionen) { this.positionen = positionen; }
}
