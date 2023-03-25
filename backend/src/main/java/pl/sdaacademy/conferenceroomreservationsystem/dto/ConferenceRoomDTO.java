package pl.sdaacademy.conferenceroomreservationsystem.dto;

import pl.sdaacademy.conferenceroomreservationsystem.entity.OrganizationEntity;
import pl.sdaacademy.conferenceroomreservationsystem.entity.ReservationEntity;

import java.util.List;

public class ConferenceRoomDTO extends AbstractBaseDTO {
    private String name;
    private Integer amountOfSpots;
    private OrganizationEntity organization;
    private List<ReservationEntity> reservations;

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

    public OrganizationEntity getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationEntity organization) {
        this.organization = organization;
    }

    public List<ReservationEntity> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationEntity> reservations) {
        this.reservations = reservations;
    }
}
