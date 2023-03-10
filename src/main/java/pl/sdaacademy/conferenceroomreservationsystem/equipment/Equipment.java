package pl.sdaacademy.conferenceroomreservationsystem.equipment;

import pl.sdaacademy.conferenceroomreservationsystem.conference_room.ConferenceRoom;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotBlank(message = "Equipment's name is mandatory")
    @Size(min = 2, max = 20)
    @Column(name = "name", nullable = false)
    private String equipmentName;

    @ManyToOne
    @Column(name = "conference_room", nullable = false)
    private ConferenceRoom conferenceRoom;

    public Equipment(String equipmentName, ConferenceRoom conferenceRoom) {
        this.equipmentName = equipmentName;
        this.conferenceRoom = conferenceRoom;
    }

    public Equipment() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }
}
