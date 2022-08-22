package com.Grandeur.GranduerBackend.repository;

import com.Grandeur.GranduerBackend.models.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstrumentRepo extends JpaRepository<Instrument,Long> {

    Optional<Instrument> getInstrumentById(Long id);

    List<Instrument> findByBrandContainingIgnoreCase(String name);
}
