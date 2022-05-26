package com.Grandeur.GranduerBackend.controller;

import com.Grandeur.GranduerBackend.DTOmodels.SearchDTO;
import com.Grandeur.GranduerBackend.models.Car;
import com.Grandeur.GranduerBackend.models.Client;
import com.Grandeur.GranduerBackend.services.CarService;
import com.Grandeur.GranduerBackend.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor

@CrossOrigin

@RestController
@RequestMapping("api/v1/cars")
public class CarController {

    private final CarService carService;

    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<List<Car>> getAllCars(){
        return new ResponseEntity<>(this.carService.getAllCars(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable("id") Long id){
        return new ResponseEntity<>(this.carService.getCarById(id),HttpStatus.FOUND);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Car>>searchCar(@RequestBody SearchDTO searchDTO){
       List<Car> cars = this.carService.getAllCars();
       List<Car>  searchResult = cars.stream()
               .filter(car -> car.getBrand().equalsIgnoreCase(searchDTO.getBrand()))
               .toList();

       if(!searchResult.isEmpty()){
           return new ResponseEntity<>(searchResult,HttpStatus.OK);
       }else {
           return new ResponseEntity<>(searchResult,HttpStatus.BAD_REQUEST);
       }
    }

//    @GetMapping("/{name}")
//    public ResponseEntity<Car>getCarsByBrandName(@PathVariable("name") String name){
////       if(this.carService.searchCar(name) != null){
////           return new ResponseEntity<>(this.carService.searchCar(name),HttpStatus.OK);
////       }else {
////           return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
////       }
//        return new ResponseEntity<>(this.carService.getCarByName(name),HttpStatus.OK);
//    }

    @PostMapping("/{seller-email}")
    public ResponseEntity<String> addCar(@PathVariable("seller-email") String email,@RequestBody Car car){
        Optional<Client> client = this.clientService.findClientByEmail(email);
        if(client.isPresent()){
            this.carService.addCar(car);
            return new ResponseEntity<>("Added car!",HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>("Please create an account to add cars",HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping
    public ResponseEntity<Car> updateCar(@RequestBody Car car){
        Car updatedCar = this.carService.updateCar(car);
        return new ResponseEntity<>(updatedCar,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable("id") Long id){
        this.carService.deleteCarById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
