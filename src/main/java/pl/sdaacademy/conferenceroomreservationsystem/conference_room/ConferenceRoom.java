package pl.sdaacademy.conferenceroomreservationsystem.conference_room;

import pl.sdaacademy.conferenceroomreservationsystem.equipment.Equipment;
import pl.sdaacademy.conferenceroomreservationsystem.reservation.Reservation;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class ConferenceRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotBlank(message = "Conference room's name is mandatory")
    @Size(min = 2, max = 20)
    @Column(name = "name", nullable = false)
    private String roomName;

    @OneToMany(mappedBy = "conference_room")
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "conference_room")
    private List<Equipment> equipment;

    public ConferenceRoom(String roomName) {
        this.roomName = roomName;
    }

    public ConferenceRoom() {
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
