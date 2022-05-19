package com.Grandeur.GranduerBackend.models;

import com.Grandeur.GranduerBackend.modelEnums.CarType;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

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

    @Lob
    private byte[] image1;
//    private String image1;

    @Lob
    private byte[] image2;

    @Lob
    private byte[] image3;

    @Lob
    private byte[] image4;


//    @ManyToOne
//    @JoinColumn(
//            nullable = false,
//            name = "client_id"
//    )
//    private Client client;


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
        this.damages = damages;
        this.ownerCount = ownerCount;
    }

}
