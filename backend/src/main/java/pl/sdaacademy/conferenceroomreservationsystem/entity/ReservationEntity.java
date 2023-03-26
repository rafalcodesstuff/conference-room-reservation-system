package pl.sdaacademy.conferenceroomreservationsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "reservation")
public class ReservationEntity extends DistributedEntity {

    @NotBlank(message = "Reservation name is mandatory")
    @Size(min = 2, max = 20)
    @Column(name = "reservation_name")
    private String name;

    @Size(min = 1)
    @Column(name = "maximum_attendees", nullable = false)
    private Short maxAttendees;

    @Column(name = "is_all_day", nullable = false)
    private Boolean isAllDay;

    @Column(name = "start_date_time", nullable = false)
    private LocalDateTime startDateTime;

    @Column(name = "end_date_time") // can be null because all-day events exist
    private LocalDateTime endDateTime;

    @OneToOne
    @JoinColumn(name = "event_organizer", nullable = false)
    private PersonEntity eventOrganizer;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "attendees")
    private List<PersonEntity> attendees;

    @ManyToOne
    @JoinColumn(name = "conference_room", nullable = false)
    private ConferenceRoomEntity conferenceRoom;

    public ReservationEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getMaxAttendees() {
        return maxAttendees;
    }

    public void setMaxAttendees(short maxAttendees) {
        this.maxAttendees = maxAttendees;
    }

    public Boolean getAllDay() {
        return isAllDay;
    }

    public void setAllDay(Boolean allDay) {
        isAllDay = allDay;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
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

    public List<PersonEntity> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<PersonEntity> attendees) {
        this.attendees = attendees;
    }
}