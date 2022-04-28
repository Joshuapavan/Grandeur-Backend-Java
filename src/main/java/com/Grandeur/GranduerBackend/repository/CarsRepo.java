package com.Grandeur.GranduerBackend.repository;

import com.Grandeur.GranduerBackend.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarsRepo extends JpaRepository<Car,Long> {

    Optional<Car> getCarById(Long id);

    List<Car> getCarByBrand(String name);

//    Optional<Client> getClientByEmail(String email);
}
