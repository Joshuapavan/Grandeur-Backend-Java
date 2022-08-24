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

    private final InstrumentService InstrumentService;

    private final ClientService clientService;

//    private final EmailRegistrationService emailRegistrationService;

    @GetMapping
    public ResponseEntity<List<Instrument>> getAllInstruments(){
        return new ResponseEntity<>(this.InstrumentService.getAllInstrument(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instrument> getInstrumentById(@PathVariable("id") Long id){
        return new ResponseEntity<>(this.InstrumentService.getInstrumentById(id),HttpStatus.FOUND);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Instrument>>searchInstrument(@RequestBody SearchDTO searchDTO){
       List<Instrument> Instruments = this.InstrumentService.getAllInstruments();
       List<Instrument>  searchResult = Instruments.stream()
               .filter(Instrument -> Instrument.getBrand().equalsIgnoreCase(searchDTO.getBrand()))
               .toList();

       if(!searchResult.isEmpty()){
           return new ResponseEntity<>(searchResult,HttpStatus.OK);
       }else {
           return new ResponseEntity<>(searchResult,HttpStatus.BAD_REQUEST);
       }
    }

    @PostMapping("/{seller-email}")
    public ResponseEntity<String> addInstrument(@PathVariable("seller-email") String email,@RequestBody Instrument Instrument){
        Optional<Client> client = this.clientService.findClientByEmail(email);
        if(client.isPresent()){
            this.InstrumentService.addInstrument(Instrument);
            return new ResponseEntity<>("Added Instrument!",HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>("Please create an account to create Instrument!",HttpStatus.NOT_FOUND);

        }
    }

    @PutMapping
    public ResponseEntity<Instrument> updateInstrument(@RequestBody Instrument Instrument){
        Instrument updatedInstrument = this.InstrumentService.updateInstrument(Instrument);
        return new ResponseEntity<>(updatedInstrument,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInstrument(@PathVariable("id") Long id){
        this.InstrumentService.deleteInstrumentById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
