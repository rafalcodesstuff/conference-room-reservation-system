package pl.sdaacademy.conferenceroomreservationsystem.dto;

import pl.sdaacademy.conferenceroomreservationsystem.entity.ConferenceRoomEntity;
import pl.sdaacademy.conferenceroomreservationsystem.entity.PersonEntity;

import java.time.LocalDateTime;
import java.util.List;

public class ReservationDTO extends AbstractBaseDTO {
    private String name;
    private short maxAttendees;
    private Boolean isAllDay;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private PersonEntity eventOrganizer;
    private List<PersonEntity> attendees;
    private ConferenceRoomEntity conferenceRoom;

    public ReservationDTO() {
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

    public List<PersonEntity> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<PersonEntity> attendees) {
        this.attendees = attendees;
    }

    public ConferenceRoomEntity getConferenceRoom() {
        return conferenceRoom;
    }

    public void setConferenceRoom(ConferenceRoomEntity conferenceRoom) {
        this.conferenceRoom = conferenceRoom;
    }
}
