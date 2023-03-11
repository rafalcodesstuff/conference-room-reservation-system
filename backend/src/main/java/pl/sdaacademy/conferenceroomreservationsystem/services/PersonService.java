package pl.sdaacademy.conferenceroomreservationsystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import pl.sdaacademy.conferenceroomreservationsystem.models.OrganizationEntity;
import pl.sdaacademy.conferenceroomreservationsystem.models.person.PersonEntity;
import pl.sdaacademy.conferenceroomreservationsystem.repository.OrganizationRepository;
import pl.sdaacademy.conferenceroomreservationsystem.repository.PersonRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final OrganizationRepository organizationRepository;

    @Autowired
    PersonService(PersonRepository personRepository, OrganizationRepository organizationRepository) {
        this.personRepository = personRepository;
        this.organizationRepository = organizationRepository;
    }

    PersonEntity getPersonByName(@NonNull String name) {
        return personRepository.findPersonByName(name)
                .orElseThrow(() -> new NoSuchElementException("Person Not Found:  " + name));
    }

    List<PersonEntity> getAllPeopleInOrganization(@NonNull String organizationName) {
        return personRepository.findAllPeopleInOrganization(organizationName);
    }

    List<PersonEntity> getAllPeopleInOrganizationASC(@NonNull String organizationName) {
        return personRepository.findAllPeopleInOrganization(organizationName);
    }

    List<PersonEntity> getAllPeopleInOrganizationDESC(@NonNull String organizationName) {
        return personRepository.findAllPeopleInOrganization(organizationName);
    }

    void addPerson(@NonNull PersonEntity personEntity) {
        personRepository.findById(personEntity.getId()).ifPresent(p -> {
            throw new RuntimeException("Element Already Exists");
        });
        personRepository.save(personEntity);
    }

    void deletePerson(@NonNull PersonEntity personEntity) {
        PersonEntity result = personRepository.findById(personEntity.getId())
                .orElseThrow(() -> new NoSuchElementException("Person not found: " + personEntity.getPersonName()));
        personRepository.delete(result);
    }

    void updatePersonName(@NonNull PersonEntity personEntity, @NonNull String newName) {
        personRepository.findById(personEntity.getId()).ifPresentOrElse(
                result -> {
                    result.setPersonName(newName);
                    personRepository.save(result);
                },
                () -> {
                    throw new NoSuchElementException("Person not found: " + personEntity.getPersonName());
                }
        );
    }

    void updatePersonOrganization(@NonNull PersonEntity personEntity, @NonNull OrganizationEntity organization) {
        // check if person exists
        personRepository.findById(personEntity.getId()).ifPresentOrElse(
                result -> {
                    // check if organization exists
                    organizationRepository.findById(organization.getId())
                            .orElseThrow(() -> new NoSuchElementException("Organization not found: " + organization.getName()));
                    result.setOrganization(organization);
                    personRepository.save(result); // save
                },
                () -> {
                    throw new NoSuchElementException("Person not found: " + personEntity.getPersonName());
                }
        );
    }

}
