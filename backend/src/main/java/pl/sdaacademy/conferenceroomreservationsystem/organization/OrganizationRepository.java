package pl.sdaacademy.conferenceroomreservationsystem.organization;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Integer> {
    @Query("SELECT org FROM Organization org WHERE org.name = :name")
    Optional<Organization> findByName(@Param("name") String name);
}
