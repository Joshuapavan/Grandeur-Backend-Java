package com.Grandeur.GranduerBackend.services.serviceImplementations;

import com.Grandeur.GranduerBackend.repository.CarsRepo;
import com.Grandeur.GranduerBackend.exceptions.CarNotFoundException;
import com.Grandeur.GranduerBackend.models.Car;
import com.Grandeur.GranduerBackend.services.CarService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor


@Service
public class CarsServiceImpl implements CarService {

    private final CarsRepo carsRepo;

    @Override
    public List<Car> getAllCars() {
        return this.carsRepo.findAll();
    }

    @Override
    public Car getCarById(Long id) {
        return this.carsRepo.getCarById(id)
                .orElseThrow(() -> new CarNotFoundException("Car with id : "+id+" doesn't Exist."));
    }

    @Override
    public Car addCar(Car car) {
        return this.carsRepo.save(car);
    }

    @Override
    public Car updateCar(Car car) {
        return this.carsRepo.save(car);
    }

    @Override
    public void deleteCarById(Long id) {
        this.carsRepo.deleteById(id);
    }


    @Override
    public Car getCarBySellerEmail(String email) {
        return this.carsRepo.getCarBySellerEmail(email);
    }

    @Override
    public List<Car> getCarsByBrand(String name) {
        return this.carsRepo.getCarByBrand(name);
    }

    @Override
    public Integer getCountOfCars() {
        return Math.toIntExact(this.carsRepo.count());
    }

//    @Override
//    public Car addImage(Car car, String image1){
//        car.setImage1(image1);
//         return this.carsRepo.save(car);
//    }



}
