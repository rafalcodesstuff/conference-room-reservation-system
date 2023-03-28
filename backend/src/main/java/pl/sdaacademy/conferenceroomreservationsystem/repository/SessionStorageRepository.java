package pl.sdaacademy.conferenceroomreservationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sdaacademy.conferenceroomreservationsystem.entity.SessionStorageEntity;

import java.util.Optional;

@Repository
public interface SessionStorageRepository extends JpaRepository<SessionStorageEntity, Integer> {
    Optional<SessionStorageEntity> findByUsername(String username);

    Optional<SessionStorageEntity> findBySessionId(String sessionId);

    void deleteBySessionId(String sessionId);
}
