package pl.sdaacademy.conferenceroomreservationsystem.dto;

import pl.sdaacademy.conferenceroomreservationsystem.PersonRoles;
import pl.sdaacademy.conferenceroomreservationsystem.entity.OrganizationEntity;
import pl.sdaacademy.conferenceroomreservationsystem.entity.ReservationEntity;

import java.util.List;

public class PersonDTO extends AbstractBaseDTO {
    private String username;
    private String email;
    private String password;
    private OrganizationEntity organization;
    private PersonRoles role;
    private List<ReservationEntity> reservations;

    public PersonDTO() {
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

    public PersonRoles getRole() {
        return role;
    }

    public void setRole(PersonRoles role) {
        this.role = role;
    }

    public List<ReservationEntity> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationEntity> reservations) {
        this.reservations = reservations;
    }
}
