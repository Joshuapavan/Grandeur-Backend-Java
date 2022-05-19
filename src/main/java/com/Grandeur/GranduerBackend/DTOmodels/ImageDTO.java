package com.Grandeur.GranduerBackend.DTOmodels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ImageDTO implements Serializable {

    private String image;
}
