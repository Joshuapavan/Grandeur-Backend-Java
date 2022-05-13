package com.Grandeur.GranduerBackend.controller;

import com.Grandeur.GranduerBackend.models.Car;
import com.Grandeur.GranduerBackend.services.CarService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor

@CrossOrigin

@RestController
@RequestMapping("api/v1/cars")
public class CarController {

    private final CarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> getAllCars(){
        return new ResponseEntity<>(this.carService.getAllCars(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable("id") Long id){
        return new ResponseEntity<>(this.carService.getCarById(id),HttpStatus.FOUND);
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<Car>>getCarsByBrandName(@PathVariable("name") String name){
        return new ResponseEntity<>(this.carService.getCarsByBrand(name),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Car> addCar(@RequestBody Car car){
        Car newCar = this.carService.addCar(car);
        return new ResponseEntity<>(newCar,HttpStatus.CREATED);
    }


    @PostMapping("/{id}/images")
    public ResponseEntity<?> addImages(@PathVariable("id") Long id
            ,@RequestParam("image1") MultipartFile image1
            ,@RequestParam("image2") MultipartFile image2
            ,@RequestParam("image3") MultipartFile image3
            ,@RequestParam("image4") MultipartFile image4)  throws IOException {

        Car newCar = carService.getCarById(id);
        carService.addImages(newCar,image1,image2,image3,image4);
        return new ResponseEntity<>(HttpStatus.OK);
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

    @GetMapping("/count")
    public ResponseEntity<Long> getCountOfCars(){
        Integer carCount = this.carService.getCountOfCars();
        return new ResponseEntity<Long>(Long.valueOf(carCount), HttpStatus.OK);
    }

}
