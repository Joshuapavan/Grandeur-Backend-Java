package com.Grandeur.GranduerBackend.registrationServices;

import com.Grandeur.GranduerBackend.repository.ClientRepo;
import com.Grandeur.GranduerBackend.repository.ConfirmationTokenRepository;
import com.Grandeur.GranduerBackend.models.ConfirmationToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor

public class ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;

    private final ClientRepo clientRepo;

    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    public void saveConfirmationToken(ConfirmationToken token){
        confirmationTokenRepository.save(token);
    }

    public void setConfirmedAt(String token) {
        confirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }

    public void deleteConfirmationToken(String token){
        Optional<ConfirmationToken> confirmationToken = confirmationTokenRepository.findByToken(token);
        confirmationToken.ifPresent(value -> confirmationTokenRepository.deleteById(value.getId()));
        if(confirmationToken.isPresent()) {
            Long id = confirmationToken.get().getClient().getId();
            clientRepo.deleteClientById(id);
        }
    }
}
