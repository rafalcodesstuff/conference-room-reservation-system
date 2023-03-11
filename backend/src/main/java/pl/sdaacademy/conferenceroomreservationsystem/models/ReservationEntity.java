package pl.sdaacademy.conferenceroomreservationsystem.models;

import pl.sdaacademy.conferenceroomreservationsystem.models.person.PersonEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "reservation")
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotBlank(message = "Reservation name is mandatory")
    @Size(min = 2, max = 20)
    @Column(name = "reservation_name")
    private String reservationName;

    @Size(min = 2)
    @Column(name = "maximum_attendees", nullable = false)
    private short maxAttendees;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "time", nullable = false)
    private LocalTime time;

    @ManyToOne
    private OrganizationEntity organization;

    @OneToOne
    private PersonEntity eventOrganizer;

    @OneToMany(cascade = CascadeType.ALL)
    private List<PersonEntity> attendees;

    @ManyToOne
    private ConferenceRoomEntity conferenceRoom;

    public ReservationEntity(String reservationName, short maxAttendees, LocalDate date, LocalTime time, OrganizationEntity organization, PersonEntity eventOrganizer, List<PersonEntity> attendees, ConferenceRoomEntity conferenceRoom) {
        this.reservationName = reservationName;
        this.maxAttendees = maxAttendees;
        this.date = date;
        this.time = time;
        this.organization = organization;
        this.eventOrganizer = eventOrganizer;
        this.attendees = attendees;
        this.conferenceRoom = conferenceRoom;
    }

    public ReservationEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReservationName() {
        return reservationName;
    }

    public void setReservationName(String reservationName) {
        this.reservationName = reservationName;
    }

    public short getMaxAttendees() {
        return maxAttendees;
    }

    public void setMaxAttendees(short maxAttendees) {
        this.maxAttendees = maxAttendees;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public OrganizationEntity getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationEntity organization) {
        this.organization = organization;
    }

    public PersonEntity getEventOrganizer() {
        return eventOrganizer;
    }

    public void setEventOrganizer(PersonEntity eventOrganizer) {
        this.eventOrganizer = eventOrganizer;
    }

    public ConferenceRoomEntity getConferenceRoom() {
        return conferenceRoom;
    }

    public void setConferenceRoom(ConferenceRoomEntity conferenceRoom) {
        this.conferenceRoom = conferenceRoom;
    }
}
