package pl.sdaacademy.conferenceroomreservationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import pl.sdaacademy.conferenceroomreservationsystem.models.person.PersonEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Integer> {
    @Query("SELECT person FROM PersonEntity person WHERE person.personName = :name")
    Optional<PersonEntity> findPersonByName(@NonNull String name);

    @Query("SELECT person FROM PersonEntity person INNER JOIN OrganizationEntity org WHERE org.organizationMembers = :name")
    List<PersonEntity> findAllPeopleInOrganization(@NonNull String name);

    @Query("SELECT person FROM PersonEntity person INNER JOIN OrganizationEntity org WHERE org.organizationMembers = :name ORDER BY person.personName ASC")
    List<PersonEntity> findAllPeopleInOrganizationASC(@NonNull String name);

    @Query("SELECT person FROM PersonEntity person INNER JOIN OrganizationEntity org WHERE org.organizationMembers = :name ORDER BY person.personName DESC")
    List<PersonEntity> findAllPeopleInOrganizationDESC(@NonNull String name);

    // This is probably useless/redundant
    @Query("UPDATE PersonEntity person SET person.personName = :newName WHERE person.personName = :previousName")
    void updatePersonName(@NonNull String previousName, @NonNull String newName);

}
