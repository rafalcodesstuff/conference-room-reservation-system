package pl.sdaacademy.conferenceroomreservationsystem.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "equipment")
public class EquipmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotBlank(message = "Equipment's name is mandatory")
    @Size(min = 2, max = 20)
    @Column(name = "name", nullable = false)
    private String equipmentName;

    @ManyToOne
    private ConferenceRoomEntity conferenceRoom;

    public EquipmentEntity(String equipmentName, ConferenceRoomEntity conferenceRoom) {
        this.equipmentName = equipmentName;
        this.conferenceRoom = conferenceRoom;
    }

    public EquipmentEntity() {
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
