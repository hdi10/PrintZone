package com.zelkulon.PrintZone.core.domain.service;

import com.zelkulon.PrintZone.core.domain.model.Lieferadresse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LieferadresseRepository extends JpaRepository<Lieferadresse, Long> {
}