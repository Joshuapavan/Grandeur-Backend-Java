package com.Grandeur.GranduerBackend.controller;

import com.sun.istack.NotNull;
import lombok.Data;

//Data tansfer object //
@Data
public class ClientDTO {

    @NotNull
    private String email;

    @NotNull
    private String password;

}
