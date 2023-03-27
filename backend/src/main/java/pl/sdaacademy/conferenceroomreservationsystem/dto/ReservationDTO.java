package pl.sdaacademy.conferenceroomreservationsystem.dto;

import pl.sdaacademy.conferenceroomreservationsystem.entity.ConferenceRoomEntity;
import pl.sdaacademy.conferenceroomreservationsystem.entity.PersonEntity;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationDTO extends AbstractBaseDTO {
    private String name;
    private short maxAttendees;
    private boolean isAllDay;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String color;
    private String eventOrganizer;
    private List<String> attendees;
    private String conferenceRoom;

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

    public void setAllDay(boolean allDay) {
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEventOrganizer() {
        return eventOrganizer;
    }

    public void setEventOrganizer(String eventOrganizer) {
        this.eventOrganizer = eventOrganizer;
    }

    public List<String> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<PersonEntity> members) {
        if (members == null) {
            this.attendees = Collections.emptyList();
            return;
        }
        this.attendees = members.stream()
                .map(PersonEntity::getUsername)
                .collect(Collectors.toList());
    }

    public String getConferenceRoom() {
        return conferenceRoom;
    }

    public void setConferenceRoom(String conferenceRoom) {
        this.conferenceRoom = conferenceRoom;
    }
}
