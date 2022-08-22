package com.Grandeur.GranduerBackend.controller;

import com.Grandeur.GranduerBackend.DTOmodels.SearchDTO;
import com.Grandeur.GranduerBackend.models.Instrument;
import com.Grandeur.GranduerBackend.models.Client;
import com.Grandeur.GranduerBackend.services.ClientService;
import com.Grandeur.GranduerBackend.services.InstrumentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor

@CrossOrigin

@RestController
@RequestMapping("api/v1/instruments")
public class InstrumentController {

    private final InstrumentService instrumentService;

    private final ClientService clientService;

//    private final EmailRegistrationService emailRegistrationService;

    @GetMapping
    public ResponseEntity<List<Instrument>> getAllCars(){
        return new ResponseEntity<>(this.instrumentService.getAllCars(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instrument> getCarById(@PathVariable("id") Long id){
        return new ResponseEntity<>(this.instrumentService.getCarById(id),HttpStatus.FOUND);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Instrument>>searchCar(@RequestBody SearchDTO searchDTO){
       List<Instrument> instruments = this.instrumentService.getAllCars();
       List<Instrument>  searchResult = instruments.stream()
               .filter(car -> car.getBrand().equalsIgnoreCase(searchDTO.getBrand()))
               .toList();

       if(!searchResult.isEmpty()){
           return new ResponseEntity<>(searchResult,HttpStatus.OK);
       }else {
           return new ResponseEntity<>(searchResult,HttpStatus.BAD_REQUEST);
       }
    }

    @PostMapping("/{seller-email}")
    public ResponseEntity<String> addCar(@PathVariable("seller-email") String email,@RequestBody Instrument instrument){
        Optional<Client> client = this.clientService.findClientByEmail(email);
        if(client.isPresent()){
            this.instrumentService.addCar(instrument);
            return new ResponseEntity<>("Added car!",HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>("Please create an account to create car!",HttpStatus.NOT_FOUND);

        }
    }


    @PutMapping
    public ResponseEntity<Instrument> updateCar(@RequestBody Instrument instrument){
        Instrument updatedInstrument = this.instrumentService.updateCar(instrument);
        return new ResponseEntity<>(updatedInstrument,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable("id") Long id){
        this.instrumentService.deleteCarById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
