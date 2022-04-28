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
    private String brand;
    private String number;
    private String model;

    @Enumerated(EnumType.STRING)
    private CarType carType;

    private String year;
    private String Kms;
    private Boolean insuranceAvailability;
    private String damages;

    @Lob
    private byte[] image1;

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


    public Car(String brand, String number, String model, CarType carType, String year, String kms, Boolean insuranceAvailability, String damages) {
        this.brand = brand;
        this.number = number;
        this.model = model;
        this.carType = carType;
        this.year = year;
        Kms = kms;
        this.insuranceAvailability = insuranceAvailability;
        this.damages = damages;
//        this.client = client;
    }


    public Car(String brand, String number, String model, CarType carType, String year, String kms,
               Boolean insuranceAvailability, String damages, byte[] image1, byte[] image2, byte[] image3, byte[] image4) {
        this.brand = brand;
        this.number = number;
        this.model = model;
        this.carType = carType;
        this.year = year;
        Kms = kms;
        this.insuranceAvailability = insuranceAvailability;
        this.damages = damages;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.image4 = image4;
//        this.client = client;
    }


}
