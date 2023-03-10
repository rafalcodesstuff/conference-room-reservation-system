package pl.sdaacademy.conferenceroomreservationsystem.reservation;

import pl.sdaacademy.conferenceroomreservationsystem.conference_room.ConferenceRoom;
import pl.sdaacademy.conferenceroomreservationsystem.organization.Organization;
import pl.sdaacademy.conferenceroomreservationsystem.person.Person;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Reservation {
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
    private Organization organization;

    @OneToOne
    private Person eventOrganizer;

    @OneToMany
    private List<Person> attendees;

    @ManyToOne
    private ConferenceRoom conferenceRoom;

    public Reservation(String reservationName, short maxAttendees, LocalDate date, LocalTime time, Organization organization, Person eventOrganizer, List<Person> attendees, ConferenceRoom conferenceRoom) {
        this.reservationName = reservationName;
        this.maxAttendees = maxAttendees;
        this.date = date;
        this.time = time;
        this.organization = organization;
        this.eventOrganizer = eventOrganizer;
        this.attendees = attendees;
        this.conferenceRoom = conferenceRoom;
    }

    public Reservation() {
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

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Person getEventOrganizer() {
        return eventOrganizer;
    }

    public void setEventOrganizer(Person eventOrganizer) {
        this.eventOrganizer = eventOrganizer;
    }

    public ConferenceRoom getConferenceRoom() {
        return conferenceRoom;
    }

    public void setConferenceRoom(ConferenceRoom conferenceRoom) {
        this.conferenceRoom = conferenceRoom;
    }
}
