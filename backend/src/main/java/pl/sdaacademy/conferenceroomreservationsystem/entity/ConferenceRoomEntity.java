package pl.sdaacademy.conferenceroomreservationsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "conference_room")
public class ConferenceRoomEntity extends DistributedEntity {

    @NotBlank(message = "Conference room's name is mandatory")
    @Size(min = 2, max = 20)
    @Column(name = "name", nullable = false)
    private String name;

    //    @Size(min = 1)
    @Column(name = "amount_of_spots", nullable = false)
    private Integer amountOfSpots;

    @ManyToOne
    private OrganizationEntity organization;

    @OneToMany(mappedBy = "conferenceRoom")
    private List<ReservationEntity> reservations;

    public ConferenceRoomEntity() {
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
