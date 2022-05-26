package com.Grandeur.GranduerBackend.DTOmodels;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchDTO implements Serializable {

    @NotNull
    private String brand;
}
