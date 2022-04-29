package com.Grandeur.GranduerBackend.controller;

import com.Grandeur.GranduerBackend.clientRegistrationRequestModel.RegistrationRequest;
import com.Grandeur.GranduerBackend.registrationServices.EmailRegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin

@RestController
@RequestMapping(path = "api/v1/registration")

@AllArgsConstructor
public class RegistrationController {

    private final EmailRegistrationService emailRegistrationService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request){
        return emailRegistrationService.register(request);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return emailRegistrationService.confirmToken(token);
    }

}
