package pl.sdaacademy.conferenceroomreservationsystem.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.sdaacademy.conferenceroomreservationsystem.entity.OrganizationEntity;

import java.util.Optional;

@Repository
public interface OrganizationRepository extends DistributedRepository<OrganizationEntity> {
    @Query("SELECT org FROM OrganizationEntity org WHERE org.name = :name")
    Optional<OrganizationEntity> findByName(@Param("name") String name);

}
