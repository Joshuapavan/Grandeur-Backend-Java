package com.Grandeur.GranduerBackend.DTOmodels;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//Data transfer object //
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO implements Serializable {

    @NotNull
    private String email;

    @NotNull
    private String password;

}
