package com.Grandeur.GranduerBackend.services.serviceImplementations;

import com.Grandeur.GranduerBackend.models.Instrument;
import com.Grandeur.GranduerBackend.repository.InstrumentRepo;
import com.Grandeur.GranduerBackend.exceptions.InstrumentNotFoundException;
import com.Grandeur.GranduerBackend.services.InstrumentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor


@Service
public class InstrumentServiceImpl implements InstrumentService {

    private final InstrumentRepo instrumentRepo;

    @Override
    public List<Instrument> getAllInstrument() {
        return this.instrumentRepo.findAll();
    }

    @Override
    public Instrument getInstrumentsById(Long id) {
        return this.instrumentRepo.getInstrumentById(id)
                .orElseThrow(() -> new InstrumentNotFoundException("Car with id : "+id+" doesn't Exist."));
    }


    @Override
    public List<Instrument> getAllInstruments() {
        return this.instrumentRepo.findAll();
    }

    @Override
    public Instrument getInstrumentById(Long id) {
        return this.instrumentRepo.getInstrumentById(id)
                .orElseThrow(() -> new InstrumentNotFoundException("Car with id : "+id+" doesn't Exist."));
    }

    @Override
    public Instrument addInstrument(Instrument instrument) {
        return this.instrumentRepo.save(instrument);
    }

    @Override
    public Instrument updateInstrument(Instrument instrument) {
        return this.instrumentRepo.save(instrument);
    }

    @Override
    public void deleteInstrumentById(Long id) {
        this.instrumentRepo.deleteById(id);
    }


//    @Override
//    public Car getCarBySellerEmail(String email) {
//        return this.carsRepo.getCarBySellerEmail(email);
//    }

//    @Override
//    public List<Instrument> searchCar(String name, String model) {
//        return this.instrumentRepo.findByBrandContainingIgnoreCase(name);
//    }

//    @Override
//    public List<Car> getCarsByBrand(String name) {
//        return this.carsRepo.getCarByBrand(name);
//    }

//    @Override
//    public Integer getCountOfCars() {
//        return Math.toIntExact(this.carsRepo.count());
//    }

}
