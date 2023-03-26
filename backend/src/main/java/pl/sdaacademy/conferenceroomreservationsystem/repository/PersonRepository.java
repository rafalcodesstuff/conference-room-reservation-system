package pl.sdaacademy.conferenceroomreservationsystem.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import pl.sdaacademy.conferenceroomreservationsystem.entity.PersonEntity;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends DistributedRepository<PersonEntity> {
    Optional<PersonEntity> findPersonByUsername(String name);

    @Query("SELECT person FROM PersonEntity person INNER JOIN OrganizationEntity org WHERE org.organizationMembers = :name")
    List<PersonEntity> findAllPeopleInOrganization(@NonNull String name);

    @Query("SELECT person FROM PersonEntity person INNER JOIN OrganizationEntity org WHERE org.organizationMembers = :name ORDER BY person.username ASC")
    List<PersonEntity> findAllPeopleInOrganizationASC(@NonNull String name);

    @Query("SELECT person FROM PersonEntity person INNER JOIN OrganizationEntity org WHERE org.organizationMembers = :name ORDER BY person.username DESC")
    List<PersonEntity> findAllPeopleInOrganizationDESC(@NonNull String name);

}
