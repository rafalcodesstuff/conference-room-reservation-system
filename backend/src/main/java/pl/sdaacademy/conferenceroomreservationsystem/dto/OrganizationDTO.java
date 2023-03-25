package pl.sdaacademy.conferenceroomreservationsystem.dto;

import pl.sdaacademy.conferenceroomreservationsystem.entity.ConferenceRoomEntity;
import pl.sdaacademy.conferenceroomreservationsystem.entity.PersonEntity;

import java.util.List;

public class OrganizationDTO extends AbstractBaseDTO {
    private String name;
    private List<PersonEntity> organizationMembers;
    private List<ConferenceRoomEntity> conferenceRooms;

    public OrganizationDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PersonEntity> getOrganizationMembers() {
        return organizationMembers;
    }

    public void setOrganizationMembers(List<PersonEntity> organizationMembers) {
        this.organizationMembers = organizationMembers;
    }

    public List<ConferenceRoomEntity> getConferenceRooms() {
        return conferenceRooms;
    }

    public void setConferenceRooms(List<ConferenceRoomEntity> conferenceRooms) {
        this.conferenceRooms = conferenceRooms;
    }
}
