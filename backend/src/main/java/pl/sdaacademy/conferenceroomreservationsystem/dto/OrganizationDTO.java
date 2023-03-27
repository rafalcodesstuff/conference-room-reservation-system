package pl.sdaacademy.conferenceroomreservationsystem.dto;

import pl.sdaacademy.conferenceroomreservationsystem.entity.ConferenceRoomEntity;
import pl.sdaacademy.conferenceroomreservationsystem.entity.PersonEntity;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class OrganizationDTO extends AbstractBaseDTO {
    private String name;
    private List<String> organizationMembers;
    private List<String> conferenceRooms;

    public OrganizationDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getOrganizationMembers() {
        return organizationMembers;
    }

    public void setOrganizationMembers(List<PersonEntity> members) {
        if (members == null) {
            this.organizationMembers = Collections.emptyList();
            return;
        }
        this.organizationMembers = members.stream()
                .map(PersonEntity::getUsername)
                .collect(Collectors.toList());
    }

    public List<String> getConferenceRooms() {
        return conferenceRooms;
    }

    public void setConferenceRooms(List<ConferenceRoomEntity> rooms) {
        if (rooms == null) {
            this.conferenceRooms = Collections.emptyList();
            return;
        }
        this.conferenceRooms = rooms.stream()
                .map(ConferenceRoomEntity::getName)
                .collect(Collectors.toList());
    }
}
