package pl.sdaacademy.conferenceroomreservationsystem.organization;

import pl.sdaacademy.conferenceroomreservationsystem.person.Person;
import pl.sdaacademy.conferenceroomreservationsystem.reservation.Reservation;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Organization {
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
    private Person ogranizationLeader;

    @OneToMany(mappedBy = "organization")
    @JoinColumn(name = "organization_members", referencedColumnName = "id")
    private List<Person> organizationMembers;

    @OneToMany(mappedBy = "organization")
    @JoinColumn(name = "reservations", referencedColumnName = "id")
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "organization")
    @JoinColumn(name = "conference_rooms", referencedColumnName = "id")
    private List<Reservation> conferenceRooms;

    public Organization(String name) {
        this.name = name;
    }

    public Organization() {
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

    public Person getOgranizationLeader() {
        return ogranizationLeader;
    }

    public void setOgranizationLeader(Person ogranizationLeader) {
        this.ogranizationLeader = ogranizationLeader;
    }
}
