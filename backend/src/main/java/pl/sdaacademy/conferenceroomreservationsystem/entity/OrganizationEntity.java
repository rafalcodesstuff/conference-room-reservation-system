package pl.sdaacademy.conferenceroomreservationsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.lang.NonNull;

import java.util.List;

@Entity
@Table(name = "organization")
public class OrganizationEntity extends DistributedEntity {

    @NonNull
    @NotBlank(message = "Organization's name is mandatory")
    @Size(min = 2, max = 20)
    @Pattern(regexp = "^[a-zA-Z0-9_-]{1,30}$", message = "Name must be alpha-numeric (plus: \"_\", \"-\") and 1 - 30 characters")
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "organization", fetch = FetchType.EAGER) // type eager because tests weren't working without it
    private List<PersonEntity> organizationMembers;

    @OneToMany(mappedBy = "organization", fetch = FetchType.EAGER) // type eager because tests weren't working without it
    private List<ConferenceRoomEntity> conferenceRooms;

    public OrganizationEntity(String name) {
        super();
        this.name = name;
    }

    public OrganizationEntity() {
        super();
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
