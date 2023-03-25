package pl.sdaacademy.conferenceroomreservationsystem.repository;

import org.springframework.stereotype.Repository;
import pl.sdaacademy.conferenceroomreservationsystem.entity.ConferenceRoomEntity;

@Repository
public interface ConferenceRoomRepository extends DistributedRepository<ConferenceRoomEntity> {
}
