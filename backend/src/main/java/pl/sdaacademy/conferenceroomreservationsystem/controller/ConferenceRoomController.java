package pl.sdaacademy.conferenceroomreservationsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sdaacademy.conferenceroomreservationsystem.api.AbstractCRUDLApi;
import pl.sdaacademy.conferenceroomreservationsystem.dto.ConferenceRoomDTO;
import pl.sdaacademy.conferenceroomreservationsystem.entity.ConferenceRoomEntity;

@RestController
@RequestMapping("/api/conference-rooms")
public class ConferenceRoomController extends AbstractCRUDLController<ConferenceRoomEntity, ConferenceRoomDTO> {
    public ConferenceRoomController(AbstractCRUDLApi<ConferenceRoomEntity, ConferenceRoomDTO> api) {
        super(api);
    }
}
