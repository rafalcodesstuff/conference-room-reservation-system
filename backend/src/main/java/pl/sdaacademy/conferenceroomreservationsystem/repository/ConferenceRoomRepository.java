package pl.sdaacademy.conferenceroomreservationsystem.repository;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.sdaacademy.conferenceroomreservationsystem.entity.ConferenceRoomEntity;
import pl.sdaacademy.conferenceroomreservationsystem.entity.OrganizationEntity;

import java.util.Optional;

@Repository
public interface ConferenceRoomRepository extends DistributedRepository<ConferenceRoomEntity> {

    Optional<ConferenceRoomEntity> findByNameAndOrganization(
            @NotBlank(message = "Conference room's name is mandatory") @Size(min = 2, max = 20) String name,
            OrganizationEntity organization);

}
