package pl.sdaacademy.conferenceroomreservationsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sdaacademy.conferenceroomreservationsystem.api.AbstractCRUDLApi;
import pl.sdaacademy.conferenceroomreservationsystem.dto.ReservationDTO;
import pl.sdaacademy.conferenceroomreservationsystem.entity.ReservationEntity;
import pl.sdaacademy.conferenceroomreservationsystem.service.ReservationService;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController extends AbstractCRUDLController<ReservationEntity, ReservationDTO> {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(AbstractCRUDLApi<ReservationEntity, ReservationDTO> api, ReservationService reservationService) {
        super(api);
        this.reservationService = reservationService;
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<ReservationDTO>> getUserReservations(@PathVariable String username) {
        List<ReservationDTO> reservations = reservationService.getUserReservations(username);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }
}
