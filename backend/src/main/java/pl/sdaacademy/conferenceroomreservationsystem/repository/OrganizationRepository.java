package pl.sdaacademy.conferenceroomreservationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.sdaacademy.conferenceroomreservationsystem.models.OrganizationEntity;

import java.util.Optional;

@Repository
public interface OrganizationRepository extends JpaRepository<OrganizationEntity, Integer> {
    @Query("SELECT org FROM OrganizationEntity org WHERE org.name = :name")
    Optional<OrganizationEntity> findByName(@Param("name") String name);
}
