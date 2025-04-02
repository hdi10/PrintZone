package com.zelkulon.PrintZone.core.domain.service;

import com.zelkulon.PrintZone.core.domain.model.Bestellung;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface BestellungRepository extends JpaRepository<Bestellung, Long> {
}
