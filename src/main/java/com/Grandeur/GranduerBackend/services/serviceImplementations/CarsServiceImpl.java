package com.Grandeur.GranduerBackend.services.serviceImplementations;

import com.Grandeur.GranduerBackend.repository.CarsRepo;
import com.Grandeur.GranduerBackend.exceptions.CarNotFoundException;
import com.Grandeur.GranduerBackend.models.Car;
import com.Grandeur.GranduerBackend.services.CarService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
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
    public void addImages(Car car, MultipartFile image1, MultipartFile image2, MultipartFile image3, MultipartFile image4) throws IOException {
        car.setImage1(Base64.getEncoder().encode(image1.getBytes()));
        car.setImage2(Base64.getEncoder().encode(image2.getBytes()));
        car.setImage3(Base64.getEncoder().encode(image3.getBytes()));
        car.setImage4(Base64.getEncoder().encode(image4.getBytes()));
        this.carsRepo.save(car);
    }

    @Override
    public List<Car> getCarsByBrand(String name) {
        return this.carsRepo.getCarByBrand(name);
    }


}
