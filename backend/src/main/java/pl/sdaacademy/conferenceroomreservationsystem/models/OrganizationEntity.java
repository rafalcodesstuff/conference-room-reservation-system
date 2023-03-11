package pl.sdaacademy.conferenceroomreservationsystem.models;

import pl.sdaacademy.conferenceroomreservationsystem.models.person.PersonEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "organization")
public class OrganizationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Organization's name is mandatory")
    @Size(min = 2, max = 20)
    private String name;

    @OneToOne
    @JoinColumn(name = "organization_leader_id", referencedColumnName = "id")
    private PersonEntity organizationLeader;

    @OneToMany(mappedBy = "organization")
    private List<PersonEntity> organizationMembers;

    @OneToMany(mappedBy = "organization")
    private List<ReservationEntity> reservations;

    @OneToMany
    private List<ConferenceRoomEntity> conferenceRoom;

    public OrganizationEntity(String name) {
        this.name = name;
    }

    public OrganizationEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PersonEntity getOrganizationLeader() {
        return organizationLeader;
    }

    public void setOrganizationLeader(PersonEntity organizationLeader) {
        this.organizationLeader = organizationLeader;
    }
}
