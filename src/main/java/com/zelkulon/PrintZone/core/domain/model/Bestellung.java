package com.zelkulon.PrintZone.core.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Bestellung")
public class Bestellung {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Bestellung_ID")
    private Long Id;

    @Column

}
