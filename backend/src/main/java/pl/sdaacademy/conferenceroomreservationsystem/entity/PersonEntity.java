package pl.sdaacademy.conferenceroomreservationsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import pl.sdaacademy.conferenceroomreservationsystem.user.UserRoles;

import java.util.List;

@Entity
@Table(name = "person")
public class PersonEntity extends DistributedEntity {

    @Size(min = 2, max = 50)
    @NotNull(message = "Username mustn't be null")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9_]{1,30}$", message = "Name must be alpha-numeric (plus: \"_\") and max 30 characters")
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @NotBlank
    @NotNull(message = "Email mustn't be null")
    @Email
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Size(min = 2, max = 65)
    @NotBlank
    @NotNull(message = "Password mustn't be null")
    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private OrganizationEntity organization;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRoles role = UserRoles.MEMBER;

    @OneToMany
    @JoinColumn(name = "attendees")
    private List<ReservationEntity> reservations;

    public PersonEntity(String username, String email, String password, OrganizationEntity organization) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.organization = organization;
    }

    public PersonEntity(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public PersonEntity() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public OrganizationEntity getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationEntity organization) {
        this.organization = organization;
    }

    public UserRoles getRole() {
        return role;
    }

    public void setRole(UserRoles role) {
        this.role = role;
    }

    public List<ReservationEntity> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationEntity> reservations) {
        this.reservations = reservations;
    }
}
