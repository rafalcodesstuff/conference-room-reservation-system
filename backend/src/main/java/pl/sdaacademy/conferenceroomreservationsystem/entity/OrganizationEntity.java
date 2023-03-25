package pl.sdaacademy.conferenceroomreservationsystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "organization")
public class OrganizationEntity extends DistributedEntity {

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Organization's name is mandatory")
    @Size(min = 2, max = 20)
    @Pattern(regexp = "^[a-zA-Z0-9_-]{1,30}$", message = "Name must be alpha-numeric (plus: \"_\", \"-\") and 1 - 30 characters")
    private String name;

    @OneToMany(mappedBy = "organization")
    private List<PersonEntity> organizationMembers;

    @OneToMany
    private List<ConferenceRoomEntity> conferenceRooms;

    public OrganizationEntity(String name) {
        this.name = name;
    }

    public OrganizationEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PersonEntity> getOrganizationMembers() {
        return organizationMembers;
    }

    public void setOrganizationMembers(List<PersonEntity> organizationMembers) {
        this.organizationMembers = organizationMembers;
    }

    public List<ConferenceRoomEntity> getConferenceRooms() {
        return conferenceRooms;
    }

    public void setConferenceRooms(List<ConferenceRoomEntity> conferenceRooms) {
        this.conferenceRooms = conferenceRooms;
    }
}
