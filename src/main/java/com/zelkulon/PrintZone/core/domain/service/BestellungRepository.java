package com.zelkulon.PrintZone.core.domain.service;

import com.zelkulon.PrintZone.core.domain.model.Bestellung;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BestellungRepository extends CrudRepository<Bestellung, Long> {

    //@Query(value = "SELECT * FROM Bestellung  WHERE Bestellung_ID = ?1", nativeQuery = true)
    @Query(value = "SELECT * FROM Bestellung",nativeQuery = true)
    List<Bestellung> getAllBestellungen();
}
