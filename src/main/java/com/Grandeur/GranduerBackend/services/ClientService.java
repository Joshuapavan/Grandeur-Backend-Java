package com.Grandeur.GranduerBackend.services;

import com.Grandeur.GranduerBackend.DTOmodels.ClientDTO;
import com.Grandeur.GranduerBackend.models.Client;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ClientService{

    List<Client> getAllClients();

    Client getClientById(Long id);

    Client addClient(Client client);

    Client updateClient(Client client);

    void deleteClientById(Long id);

    String isValidCredentials(ClientDTO clientDTO) throws Exception;

    Optional<Client> findClientByEmail(String email);

    Optional<Client> getClientByName(String name);
}
