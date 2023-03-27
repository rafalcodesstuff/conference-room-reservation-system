package pl.sdaacademy.conferenceroomreservationsystem.dto;

import pl.sdaacademy.conferenceroomreservationsystem.converter.ConferenceRoomDTOConverter;
import pl.sdaacademy.conferenceroomreservationsystem.converter.ReservationDTOConverter;
import pl.sdaacademy.conferenceroomreservationsystem.entity.OrganizationEntity;
import pl.sdaacademy.conferenceroomreservationsystem.entity.ReservationEntity;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ConferenceRoomDTO extends AbstractBaseDTO {
    private String name;
    private Integer amountOfSpots;
    private String organization;
    private List<ReservationDTO> reservations;

    public ConferenceRoomDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmountOfSpots() {
        return amountOfSpots;
    }

    public void setAmountOfSpots(Integer amountOfSpots) {
        this.amountOfSpots = amountOfSpots;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationEntity organization) {
        this.organization = organization.getName();
    }

    public List<ReservationDTO> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationEntity> reservations) {
        if (reservations == null) {
            this.reservations = Collections.emptyList();
            return;
        }
        this.reservations = reservations.stream()
                .map(reservation -> new ReservationDTOConverter().convert(reservation))
                .collect(Collectors.toList());
    }
}
