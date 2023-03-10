package pl.sdaacademy.conferenceroomreservationsystem.person;

import pl.sdaacademy.conferenceroomreservationsystem.organization.Organization;
import pl.sdaacademy.conferenceroomreservationsystem.reservation.Reservation;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotBlank(message = "Person's name is mandatory")
    @Size(min = 2, max = 30)
    @Column(name = "name", nullable = false)
    private String personName;

    @ManyToOne
    @Column(name = "organization", nullable = false)
    private Organization organization;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Roles role = Roles.MEMBER;

    @OneToMany
    @JoinColumn(name = "reservations")
    private List<Reservation> reservations;

    public Person(String personName, Organization organization) {
        this.personName = personName;
        this.organization = organization;
    }

    public Person() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}
