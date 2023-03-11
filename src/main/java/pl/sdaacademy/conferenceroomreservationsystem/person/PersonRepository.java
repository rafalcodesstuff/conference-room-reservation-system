package pl.sdaacademy.conferenceroomreservationsystem.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    @Query("SELECT person FROM Person person WHERE person.personName = :name")
    Optional<Person> findPersonByName(@NonNull String name);

    @Query("SELECT person FROM Person person INNER JOIN Organization org WHERE org.organizationMembers = :name")
    List<Person> findAllPeopleInOrganization(@NonNull String name);

    @Query("SELECT person FROM Person person INNER JOIN Organization org WHERE org.organizationMembers = :name ORDER BY person.personName ASC")
    List<Person> findAllPeopleInOrganizationASC(@NonNull String name);

    @Query("SELECT person FROM Person person INNER JOIN Organization org WHERE org.organizationMembers = :name ORDER BY person.personName DESC")
    List<Person> findAllPeopleInOrganizationDESC(@NonNull String name);

    // This is probably useless/redundant
    @Query("UPDATE Person person SET person.personName = :newName WHERE person.personName = :previousName")
    void updatePersonName(@NonNull String previousName, @NonNull String newName);

}
