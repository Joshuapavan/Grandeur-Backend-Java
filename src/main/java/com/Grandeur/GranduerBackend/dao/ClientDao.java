package com.Grandeur.GranduerBackend.dao;

import com.Grandeur.GranduerBackend.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientDao extends JpaRepository<Client,Long> {

    void deleteClientById(Long id);

    Optional<Client> getClientById(Long id);
}
