package pl.sdaacademy.conferenceroomreservationsystem.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sdaacademy.conferenceroomreservationsystem.entity.SessionStorageEntity;
import pl.sdaacademy.conferenceroomreservationsystem.exception.RecordNotFoundException;
import pl.sdaacademy.conferenceroomreservationsystem.repository.SessionStorageRepository;

@Service
public class SessionStorageService {

    private final SessionStorageRepository repository;

    @Autowired
    public SessionStorageService(SessionStorageRepository repository) {
        this.repository = repository;
    }

    public String getSessionUsername(String token) {
        SessionStorageEntity storageEntity = repository.findBySessionId(token)
                .orElseThrow(() -> new RecordNotFoundException("Failed to find user with token: " + token));
        return storageEntity.getUsername();
    }

    @Transactional
    public void addSessionToken(String username, String token) {
        SessionStorageEntity storageEntity = repository.findByUsername(username)
                .orElse(new SessionStorageEntity());
        storageEntity.setUsername(username);
        storageEntity.setSessionId(token);
        repository.save(storageEntity);
    }

    @Transactional // without this annotation, method doesn't work, I don't know why
    public void removeSessionToken(String token) {
        SessionStorageEntity storageEntity = repository.findBySessionId(token)
                .orElseThrow(() -> new RecordNotFoundException("Failed to find user with token: " + token));
        repository.deleteBySessionId(token);
    }
}
