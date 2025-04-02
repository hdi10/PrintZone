package com.zelkulon.PrintZone.port.controller.dto;

import jakarta.validation.constraints.NotBlank;

public class KundeDto {
    private Long id;
    @NotBlank(message = "Name darf nicht leer sein")
    private String name;

    public KundeDto() { }

    public KundeDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters and setters...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}