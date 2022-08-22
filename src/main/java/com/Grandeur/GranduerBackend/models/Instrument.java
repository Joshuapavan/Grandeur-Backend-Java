package com.Grandeur.GranduerBackend.models;

import com.Grandeur.GranduerBackend.modelEnums.instrumentType;
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

@Entity(name = "instruments")
public class Instrument {

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
    private instrumentType instrumentType;

    private String year;

    private String price;
    private String damages;
    private String imageURL;


    public Instrument(String sellerName
            , String sellerEmail
            , String brand
            , String model
            , String year
            , String price
            , String imageURL
            , String damages
            , instrumentType instrumentType) {

        this.sellerName = sellerName;
        this.sellerEmail = sellerEmail;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.instrumentType = instrumentType;
        this.year = year;
        this.imageURL = imageURL;
        this.damages = damages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Instrument instrument = (Instrument) o;
        return id != null && Objects.equals(id, instrument.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
