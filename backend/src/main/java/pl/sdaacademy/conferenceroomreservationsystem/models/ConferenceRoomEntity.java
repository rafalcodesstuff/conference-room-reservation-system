package pl.sdaacademy.conferenceroomreservationsystem.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "conference_room")
public class ConferenceRoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotBlank(message = "Conference room's name is mandatory")
    @Size(min = 2, max = 20)
    @Column(name = "name", nullable = false)
    private String roomName;

    @Size(min = 2)
    @Column(name = "amount_of_spots", nullable = false)
    private Short amountOfSpots;

    @ManyToOne
    private OrganizationEntity organization;

    @OneToMany(mappedBy = "conferenceRoom")
    private List<ReservationEntity> reservations;

    @OneToMany(mappedBy = "conferenceRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EquipmentEntity> equipment;

    public ConferenceRoomEntity(String roomName) {
        this.roomName = roomName;
    }

    public ConferenceRoomEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
