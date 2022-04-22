package com.Grandeur.GranduerBackend.Repository;

import com.Grandeur.GranduerBackend.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface ClientRepo extends JpaRepository<Client,Long> {

    void deleteClientById(Long id);

    Optional<Client> getClientById(Long id);

    Optional<Client> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE Client a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableClient(String email);
}
