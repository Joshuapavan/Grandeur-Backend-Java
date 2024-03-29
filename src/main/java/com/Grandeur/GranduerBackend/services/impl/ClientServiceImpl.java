package com.Grandeur.GranduerBackend.services.impl;

import com.Grandeur.GranduerBackend.models.ConfirmationToken;
import com.Grandeur.GranduerBackend.Repository.ClientRepo;
import com.Grandeur.GranduerBackend.exceptions.ClientNotFoundException;
import com.Grandeur.GranduerBackend.exceptions.EmailAlreadyTakenException;
import com.Grandeur.GranduerBackend.models.Client;
import com.Grandeur.GranduerBackend.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService, UserDetailsService {

    private final ClientRepo clientRepo;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final  ConfirmationTokenService confirmationTokenService;

    @Override
    public List<Client> getAllClients() {
        return this.clientRepo.findAll();
    }

    @Override
    public Client getClientById(Long id) {
        return this.clientRepo.getClientById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client with id : "+id+" doesn't Exist."));
    }

    @Override
    public Client addClient(Client client) {
        return this.clientRepo.save(client);
    }

    @Override
    public Client updateClient(Client client) {
        return this.clientRepo.save(client);
    }

    @Override
    public void deleteClientById(Long id) {
        this.clientRepo.deleteClientById(id);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.clientRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User with email "+email+" does not exist!"));
    }

    public String signUpClient(Client client) {
        boolean clientExist = clientRepo.findByEmail(client.getEmail()).isPresent();

        if(clientExist){
            throw new EmailAlreadyTakenException("Email already taken!");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(client.getPassword()); // Encrypting the password //
        client.setPassword(encodedPassword);
        clientRepo.save(client); // storing it in the database //

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken =  new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                client
        );


        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return token;

    }

    public int enableClient (String email) {
        return clientRepo.enableClient(email);
    }
}
