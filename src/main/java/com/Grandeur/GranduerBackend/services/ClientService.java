package com.Grandeur.GranduerBackend.services;

import com.Grandeur.GranduerBackend.models.Client;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientService{

    List<Client> getAllClients();

    Client getClientById(Long id);

    Client addClient(Client client);

    Client updateClient(Client client);

    void deleteClientById(Long id);
}
