package com.Grandeur.GranduerBackend.DTOmodels;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class InstrumentDTO implements Serializable {
    @NotNull
    private String carName;
}
