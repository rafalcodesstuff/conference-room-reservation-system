package pl.sdaacademy.conferenceroomreservationsystem.models.person;

import pl.sdaacademy.conferenceroomreservationsystem.models.OrganizationEntity;
import pl.sdaacademy.conferenceroomreservationsystem.models.ReservationEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "person")
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotBlank(message = "Person's name is mandatory")
    @Size(min = 2, max = 30)
    @Column(name = "name", nullable = false)
    private String personName;

    @ManyToOne
    private OrganizationEntity organization;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Roles role = Roles.MEMBER;

    @OneToMany
    private List<ReservationEntity> reservations;

    public PersonEntity(String personName, OrganizationEntity organization) {
        this.personName = personName;
        this.organization = organization;
    }

    public PersonEntity() {
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

    public OrganizationEntity getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationEntity organization) {
        this.organization = organization;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}
