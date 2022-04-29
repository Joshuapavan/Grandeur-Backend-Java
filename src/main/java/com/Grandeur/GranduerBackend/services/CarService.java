package com.Grandeur.GranduerBackend.services;

import com.Grandeur.GranduerBackend.models.Car;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface CarService {

    List<Car> getAllCars();

    Car getCarById(Long id);

    Car addCar(Car car);

    Car updateCar(Car car);

    void deleteCarById(Long id);

    void addImages(Car car,MultipartFile image1, MultipartFile image2, MultipartFile image3, MultipartFile image4) throws IOException;

    List<Car> getCarsByBrand(String name);

    Integer getCountOfCars();
}
