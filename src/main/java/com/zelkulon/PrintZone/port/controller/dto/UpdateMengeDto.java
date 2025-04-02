package com.zelkulon.PrintZone.port.controller.dto;

import jakarta.validation.constraints.Min;

public class UpdateMengeDto {
    @Min(value = 1, message = "Menge muss mindestens 1 sein")
    private int menge;

    public UpdateMengeDto() { }

    public UpdateMengeDto(int menge) {
        this.menge = menge;
    }

    // Getter and setter...
    public int getMenge() { return menge; }
    public void setMenge(int menge) { this.menge = menge; }
}