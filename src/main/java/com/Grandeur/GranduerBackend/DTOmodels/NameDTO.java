package com.Grandeur.GranduerBackend.DTOmodels;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NameDTO implements Serializable {

    @NotNull
    private String name;

    @NotNull
    private String email;

}
