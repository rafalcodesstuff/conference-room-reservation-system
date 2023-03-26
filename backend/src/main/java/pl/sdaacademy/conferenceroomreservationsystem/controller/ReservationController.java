package pl.sdaacademy.conferenceroomreservationsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sdaacademy.conferenceroomreservationsystem.api.AbstractCRUDLApi;
import pl.sdaacademy.conferenceroomreservationsystem.dto.ReservationDTO;
import pl.sdaacademy.conferenceroomreservationsystem.entity.ReservationEntity;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController extends AbstractCRUDLController<ReservationEntity, ReservationDTO> {
    public ReservationController(AbstractCRUDLApi<ReservationEntity, ReservationDTO> api) {
        super(api);
    }
}
