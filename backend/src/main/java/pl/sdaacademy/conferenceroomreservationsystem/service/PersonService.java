package pl.sdaacademy.conferenceroomreservationsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sdaacademy.conferenceroomreservationsystem.api.PersonApi;
import pl.sdaacademy.conferenceroomreservationsystem.converter.PersonDTOConverter;
import pl.sdaacademy.conferenceroomreservationsystem.dto.PersonDTO;
import pl.sdaacademy.conferenceroomreservationsystem.entity.OrganizationEntity;
import pl.sdaacademy.conferenceroomreservationsystem.entity.PersonEntity;
import pl.sdaacademy.conferenceroomreservationsystem.exception.RecordNotFoundException;
import pl.sdaacademy.conferenceroomreservationsystem.repository.OrganizationRepository;
import pl.sdaacademy.conferenceroomreservationsystem.repository.PersonRepository;
import pl.sdaacademy.conferenceroomreservationsystem.user.UserRoles;

import java.util.Optional;

@Service
public class PersonService extends AbstractCRUDLService<PersonEntity, PersonDTO> implements PersonApi {

    private final PersonRepository personRepository;
    private final OrganizationRepository organizationRepository;


    @Autowired
    public PersonService(PersonRepository repository, PersonDTOConverter converter, OrganizationRepository organizationRepository) {
        super(repository, converter);
        this.personRepository = repository;
        this.organizationRepository = organizationRepository;
    }

    @Override
    protected void updateEntity(PersonEntity entity, PersonDTO dto) {
        if (dto.isNew()) {
            entity.setUsername(dto.getUsername());
            entity.setEmail(dto.getEmail());
            entity.setPassword(dto.getPassword());

            OrganizationEntity organization = organizationRepository.findByName(dto.getOrganization())
                    .orElseThrow(() -> new RecordNotFoundException("Failed to find Organization: " + dto.getOrganization()));
            entity.setOrganization(organization);

            entity.setRole(UserRoles.MEMBER);
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
                OrganizationEntity organization = organizationRepository.findByName(dto.getOrganization())
                        .orElseThrow(() -> new RecordNotFoundException("Failed to find Organization: " + dto.getOrganization()));
                entity.setOrganization(organization);
            }
            if (dto.getRole() != null) {
                entity.setRole(dto.getRole());
            }
        }
    }

    public Optional<PersonEntity> getPersonByUsername(String name) {
        return personRepository.findPersonByUsername(name);
    }

}
