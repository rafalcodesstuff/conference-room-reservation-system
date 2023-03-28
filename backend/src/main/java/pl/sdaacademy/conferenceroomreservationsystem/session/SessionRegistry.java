package pl.sdaacademy.conferenceroomreservationsystem.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.sdaacademy.conferenceroomreservationsystem.service.SessionStorageService;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

@Component
public class SessionRegistry {
//    private static final HashMap<String, String> SESSIONS = new HashMap<>();

    private final SessionStorageService sessionStorageService;

    @Autowired
    public SessionRegistry(SessionStorageService sessionStorageService) {
        this.sessionStorageService = sessionStorageService;
    }

    public String registerSession(String username) {
        if (username == null) {
            throw new RuntimeException("Username needs to be provided");
        }

        final String sessionId = generateSessionId();
        sessionStorageService.addSessionToken(username, sessionId);
        return sessionId;
    }

    public void removeSession(String sessionId) {
        if (sessionId == null) {
            throw new RuntimeException("Username needs to be provided");
        }

//        String username = getUsernameForSession(sessionId);
        sessionStorageService.removeSessionToken(sessionId);
//        SESSIONS.remove(username);

    }

    public String getUsernameForSession(String sessionId) {
        return sessionStorageService.getSessionUsername(sessionId);
//        return SESSIONS.get(sessionId);
    }

    private String generateSessionId() {
        return new String(
                Base64.getEncoder().encode(
                        UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8)
                )
        );
    }
}
