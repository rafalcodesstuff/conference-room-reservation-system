package pl.sdaacademy.conferenceroomreservationsystem.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import pl.sdaacademy.conferenceroomreservationsystem.organization.Organization;
import pl.sdaacademy.conferenceroomreservationsystem.organization.OrganizationRepository;

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

    Person getPersonByName(@NonNull String name) {
        return personRepository.findPersonByName(name)
                .orElseThrow(() -> new NoSuchElementException("Person Not Found:  " + name));
    }

    List<Person> getAllPeopleInOrganization(@NonNull String organizationName) {
        return personRepository.findAllPeopleInOrganization(organizationName);
    }

    List<Person> getAllPeopleInOrganizationASC(@NonNull String organizationName) {
        return personRepository.findAllPeopleInOrganization(organizationName);
    }

    List<Person> getAllPeopleInOrganizationDESC(@NonNull String organizationName) {
        return personRepository.findAllPeopleInOrganization(organizationName);
    }

    void addPerson(@NonNull Person person) {
        personRepository.findById(person.getId()).ifPresent(p -> {
            throw new RuntimeException("Element Already Exists");
        });
        personRepository.save(person);
    }

    void deletePerson(@NonNull Person person) {
        Person result = personRepository.findById(person.getId())
                .orElseThrow(() -> new NoSuchElementException("Person not found: " + person.getPersonName()));
        personRepository.delete(result);
    }

    void updatePersonName(@NonNull Person person, @NonNull String newName) {
        personRepository.findById(person.getId()).ifPresentOrElse(
                result -> {
                    result.setPersonName(newName);
                    personRepository.save(result);
                },
                () -> {
                    throw new NoSuchElementException("Person not found: " + person.getPersonName());
                }
        );
    }

    void updatePersonOrganization(@NonNull Person person, @NonNull Organization organization) {
        // check if person exists
        personRepository.findById(person.getId()).ifPresentOrElse(
                result -> {
                    // check if organization exists
                    organizationRepository.findById(organization.getId())
                            .orElseThrow(() -> new NoSuchElementException("Organization not found: " + organization.getName()));
                    result.setOrganization(organization);
                    personRepository.save(result); // save
                },
                () -> {
                    throw new NoSuchElementException("Person not found: " + person.getPersonName());
                }
        );
    }

}
