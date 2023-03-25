package pl.sdaacademy.conferenceroomreservationsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sdaacademy.conferenceroomreservationsystem.PersonRoles;
import pl.sdaacademy.conferenceroomreservationsystem.api.PersonApi;
import pl.sdaacademy.conferenceroomreservationsystem.converter.PersonDTOConverter;
import pl.sdaacademy.conferenceroomreservationsystem.dto.PersonDTO;
import pl.sdaacademy.conferenceroomreservationsystem.entity.PersonEntity;
import pl.sdaacademy.conferenceroomreservationsystem.repository.PersonRepository;

@Service
public class PersonService extends AbstractCRUDLService<PersonEntity, PersonDTO> implements PersonApi {

    @Autowired
    public PersonService(final PersonRepository repository,
                         final PersonDTOConverter converter) {
        super(repository, converter);
    }

    @Override
    protected void updateEntity(PersonEntity entity, PersonDTO dto) {
        if (dto.isNew()) {
            entity.setUsername(dto.getUsername());
            entity.setEmail(dto.getEmail());
            entity.setPassword(dto.getPassword());
            entity.setOrganization(dto.getOrganization());
            entity.setRole(PersonRoles.MEMBER);
        } else {
            if (dto.getUsername() != null) {
                entity.setUsername(dto.getUsername());
            }
            if (dto.getEmail() != null) {
                entity.setEmail(dto.getEmail());
            }
            if (dto.getPassword() != null) {
                entity.setPassword(dto.getPassword());
            }
            if (dto.getOrganization() != null) {
                entity.setOrganization(dto.getOrganization());
            }
            if (dto.getRole() != null) {
                entity.setRole(dto.getRole());
            }
        }
    }
//
//    public PersonEntity getPersonByName(String name) {
//        return personRepository.findPersonByUsername(name)
//                .orElseThrow(() -> new NoSuchElementException("Person Not Found: " + name));
//
////        return personRepository.findPersonByUsername(name)
////                .orElseThrow(() -> new NoSuchElementException("Person Not Found:  " + name));
//    }

//    @NonNull
//    @Email
//    public PersonEntity getPersonByEmail(String email) {
//        return personRepository.findPersonByEmail(email)
//                .orElseThrow(() -> new PersonNotFoundException());
//    }
//
//    @NonNull
//    @Pattern(regexp = "^[a-zA-Z0-9_-]{1,30}$", message = "Name must be alpha-numeric (plus: \"_\", \"-\") and 1 - 30 characters")
//    public List<PersonEntity> getAllPeopleInOrganization(String organizationName) {
//        return personRepository.findAllPeopleInOrganization(organizationName);
//    }
//
//    public List<PersonEntity> getAllPeopleInOrganizationASC(@NonNull String organizationName) {
//        return personRepository.findAllPeopleInOrganization(organizationName);
//    }
//
//    public List<PersonEntity> getAllPeopleInOrganizationDESC(@NonNull String organizationName) {
//        return personRepository.findAllPeopleInOrganization(organizationName);
//    }

}
