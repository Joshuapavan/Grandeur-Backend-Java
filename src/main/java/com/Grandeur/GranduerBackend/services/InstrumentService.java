package com.Grandeur.GranduerBackend.services;

import com.Grandeur.GranduerBackend.models.Instrument;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InstrumentService {

    List<Instrument> getAllInstruments();

    Instrument getInstrumentById(Long id);

    Instrument addInstrument(Instrument instrument);

    Instrument updateInstrument(Instrument instrument);

    void deleteInstrumentById(Long id);

    List<Instrument> getAllInstrument();

    Instrument getInstrumentsById(Long id);
}
