package pl.sdaacademy.conferenceroomreservationsystem.converter;

import org.springframework.stereotype.Component;
import pl.sdaacademy.conferenceroomreservationsystem.dto.PersonDTO;
import pl.sdaacademy.conferenceroomreservationsystem.entity.PersonEntity;

@Component
public class PersonDTOConverter extends AbstractDTOConverter<PersonEntity, PersonDTO> {
    @Override
    public PersonDTO convert(final PersonEntity entity) {
        final PersonDTO personDTO = new PersonDTO();
        super.convert(entity, personDTO);

        personDTO.setUsername(entity.getUsername());
        personDTO.setEmail(entity.getEmail());
        personDTO.setPassword(entity.getPassword());
        personDTO.setOrganization(entity.getOrganization());
        personDTO.setRole(entity.getRole());
        personDTO.setReservations(entity.getReservations());

        return personDTO;
    }
}
