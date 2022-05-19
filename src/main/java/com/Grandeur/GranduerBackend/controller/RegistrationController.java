package com.Grandeur.GranduerBackend.controller;

import com.Grandeur.GranduerBackend.DTOmodels.ClientDTO;
import com.Grandeur.GranduerBackend.DTOmodels.NameDTO;
import com.Grandeur.GranduerBackend.clientRegistrationRequestModel.RegistrationRequest;
import com.Grandeur.GranduerBackend.registrationServices.EmailRegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin

@RestController
@RequestMapping(path = "api/v1/registration")

@AllArgsConstructor
public class RegistrationController {

    private final EmailRegistrationService emailRegistrationService;

    @PostMapping
    public ResponseEntity<NameDTO> register(@RequestBody RegistrationRequest request){
//        return emailRegistrationService.register(request);
        return new ResponseEntity<>(emailRegistrationService.register(request), HttpStatus.OK);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return emailRegistrationService.confirmToken(token);
    }

}
