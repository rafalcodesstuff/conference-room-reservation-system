package pl.sdaacademy.conferenceroomreservationsystem.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.sdaacademy.conferenceroomreservationsystem.entity.PersonEntity;
import pl.sdaacademy.conferenceroomreservationsystem.repository.PersonRepository;

@Service
public class CurrentUserService implements UserDetailsService {

    private final PersonRepository repository;

    @Autowired
    public CurrentUserService(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public CurrentUser loadUserByUsername(String username) throws UsernameNotFoundException {
        PersonEntity user = repository.findPersonByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Failed to find user with username: " + username));

        CurrentUser currentUser = new CurrentUser();
        currentUser.setUsername(user.getUsername());
        currentUser.setPassword(user.getPassword());
        return currentUser;
    }
}
