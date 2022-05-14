package com.Grandeur.GranduerBackend.controller;

import com.Grandeur.GranduerBackend.models.Client;
import com.Grandeur.GranduerBackend.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor

@CrossOrigin

@RestController   // Specifying it as a rest controller //
@RequestMapping("/api/v1/clients") // path/ Map to access the Api //
public class ClientController {

    private final ClientService clientService;

//    @Autowired // dependency injection //
//    public ClientController(ClientService clientService) {
//        this.clientService = clientService;
//    }e

    // api path to get all the clients //  GET Request //
    @GetMapping
    public ResponseEntity<List<Client>> getAllClients(){ // Getting all the Clients from the Client service //
        return new ResponseEntity<>(this.clientService.getAllClients(), HttpStatus.OK); // Creating a new response entity from Clients and passing the response code along with it //
    }

    // api path to get the user based on the provided id //  GET Request //
    @GetMapping("/{id}")  // getting a user by specific id //
    public ResponseEntity<Client> getClientById(@PathVariable("id") Long id){ // getting the id from mapped request //
        Client client = this.clientService.getClientById(id); // Getting the user from the user service //
        return new ResponseEntity<>(client,HttpStatus.OK); // Creating a new response entity from users and passing the response code along with it //
    }

    // api path to create / add a user // POST Request //
    @PostMapping
    public ResponseEntity<Client> addClients(@RequestBody Client client){  // type casting the JSON Data into  a user object //
        Client newClient = this.clientService.addClient(client); // adding the posted user data to new user //
        return new ResponseEntity<>(newClient,HttpStatus.ACCEPTED); // Returning the response code along with the new user instance //
    }

    // api path to update an existing user // PUT Request //
    @PutMapping
    public ResponseEntity<Client> updateClient(@RequestBody Client client){ // type casting the JSON Data into  a user object //
        Client updatedClient = this.clientService.updateClient(client); // adding the posted user data to updated user //
        return new ResponseEntity<>(updatedClient,HttpStatus.OK);  // Returning the response code along with the updated user instance //
    }

    // api path to delete user // DELETE Request //
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable("id") Long id){ // getting the id from the user //
        this.clientService.deleteClientById(id); // deleting the user //
        return new ResponseEntity<>(HttpStatus.OK); //  returning the status code //
    }

    @GetMapping("/{email}&{password}")
    public Boolean isPasswordMatching(@PathVariable("email") String email, @PathVariable("password")String password) {
        return this.clientService.isValidCredentials(email, password);
    }
}
