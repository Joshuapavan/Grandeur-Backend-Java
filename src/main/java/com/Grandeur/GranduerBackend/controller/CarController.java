package com.Grandeur.GranduerBackend.controller;

import com.Grandeur.GranduerBackend.DTOmodels.ImageDTO;
import com.Grandeur.GranduerBackend.models.Car;
import com.Grandeur.GranduerBackend.models.Client;
import com.Grandeur.GranduerBackend.services.CarService;
import com.Grandeur.GranduerBackend.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{name}")
    public ResponseEntity<List<Car>>getCarsByBrandName(@PathVariable("name") String name){
        return new ResponseEntity<>(this.carService.getCarsByBrand(name),HttpStatus.OK);
    }

    @PostMapping("/{seller-email}")
    public ResponseEntity<String> addCar(@PathVariable("seller-email") String email,@RequestBody Car car){
        Optional<Client> client = this.clientService.findClientByEmail(email);
        if(client.isPresent()){
            Car newCar = this.carService.addCar(car);
            return new ResponseEntity<>("Added car!",HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>("Please create an account to add cars",HttpStatus.NOT_FOUND);
        }
    }


//    @PostMapping("/{id}/images")
//    public ResponseEntity<?> addImages(@PathVariable("id") Long id
//            ,@RequestParam("image1") MultipartFile image1
//            ,@RequestParam("image2") MultipartFile image2
//            ,@RequestParam("image3") MultipartFile image3
//            ,@RequestParam("image4") MultipartFile image4)  throws IOException {
//
//        Car newCar = carService.getCarById(id);
//        carService.addImages(newCar,image1,image2,image3,image4);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    @PostMapping("/{seller-email}/image1")
    public ResponseEntity<String> addImages(@PathVariable("seller-email") String email
            , @RequestBody ImageDTO image) {

        Car car = carService.getCarBySellerEmail(email);

        if(image == null){
            return new ResponseEntity<>("Upload Failed, Please try again",HttpStatus.NO_CONTENT);
        }

        if(car != null) {
            carService.addImage(car, image.getImage());
            return new ResponseEntity<>("Successfully added the image",HttpStatus.OK);
        }
        if(car == null){
            return new ResponseEntity<>("Cannot find Seller with the email "+email,HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>("Unable to add the image", HttpStatus.NOT_ACCEPTABLE);
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

    @GetMapping("/count")
    public ResponseEntity<Long> getCountOfCars(){
        Integer carCount = this.carService.getCountOfCars();
        return new ResponseEntity<Long>(Long.valueOf(carCount), HttpStatus.OK);
    }

}
