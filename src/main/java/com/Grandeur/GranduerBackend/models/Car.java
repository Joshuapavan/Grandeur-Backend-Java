package com.Grandeur.GranduerBackend.models;

import com.Grandeur.GranduerBackend.modelEnums.CarType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "Cars")
public class Car {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy =  GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;

    private String sellerName;
    private String sellerEmail;
    private String brand;
    private String model;
    private String number;

    @Enumerated(EnumType.STRING)
    private CarType carType;

    private String year;
    private String Kms;
    private String expectedPrice;
    private Boolean insuranceAvailability;
    private String damages;
    private String ownerCount;
    private String imageURL;


    public Car(String sellerName
            ,String sellerEmail
            ,String brand
            ,String model
            ,String number
            ,String year
            ,String kms
            ,String expectedPrice
            ,String ownerCount
            ,Boolean insuranceAvailability
            ,String imageURL
            ,String damages
            ,CarType carType) {

        this.sellerName = sellerName;
        this.sellerEmail = sellerEmail;
        this.brand = brand;
        this.number = number;
        this.model = model;
        this.expectedPrice = expectedPrice;
        this.carType = carType;
        this.year = year;
        this.Kms = kms;
        this.insuranceAvailability = insuranceAvailability;
        this.imageURL = imageURL;
        this.damages = damages;
        this.ownerCount = ownerCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Car car = (Car) o;
        return id != null && Objects.equals(id, car.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
