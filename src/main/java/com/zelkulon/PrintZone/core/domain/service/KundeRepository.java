package com.zelkulon.PrintZone.core.domain.service;

import com.zelkulon.PrintZone.core.domain.model.Kunde;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KundeRepository extends JpaRepository<Kunde, Long> {
}