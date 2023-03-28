package pl.sdaacademy.conferenceroomreservationsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "session_storage")
public class SessionStorageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NonNull
    @NotBlank
    @Column(name = "username", nullable = false)
    private String username;

    @NonNull
    @NotBlank
    @Column(name = "session_id", nullable = false)
    private String sessionId;

    public SessionStorageEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
