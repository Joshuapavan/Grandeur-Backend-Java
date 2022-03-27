package com.Grandeur.GranduerBackend.services.impl;

import com.Grandeur.GranduerBackend.dao.ClientDao;
import com.Grandeur.GranduerBackend.exceptions.ClientNotFoundException;
import com.Grandeur.GranduerBackend.models.Client;
import com.Grandeur.GranduerBackend.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClientServiceImpl implements ClientService {

    private final ClientDao clientDao;

    @Autowired
    public ClientServiceImpl(ClientDao clientDao) {
        this.clientDao = clientDao;
    }


    @Override
    public List<Client> getAllClients() {
        return this.clientDao.findAll();
    }

    @Override
    public Client getClientById(Long id) {
        return this.clientDao.getClientById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client with id = "+id+" doesn't Exist."));
    }

    @Override
    public Client addClient(Client client) {
        return this.clientDao.save(client);
    }

    @Override
    public Client updateClient(Client client) {
        return this.clientDao.save(client);
    }

    @Override
    public void deleteClientById(Long id) {
        this.clientDao.deleteClientById(id);
    }
}
