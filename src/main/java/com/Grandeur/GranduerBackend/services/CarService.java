package com.Grandeur.GranduerBackend.services;

import com.Grandeur.GranduerBackend.models.Car;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarService {

    List<Car> getAllCars();

    Car getCarById(Long id);

    Car addCar(Car car);

    Car updateCar(Car car);

    void deleteCarById(Long id);
    List<Car> searchCar(String name, String model);
}
