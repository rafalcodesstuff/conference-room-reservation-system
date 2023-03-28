package pl.sdaacademy.conferenceroomreservationsystem.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sdaacademy.conferenceroomreservationsystem.api.AbstractCRUDLApi;
import pl.sdaacademy.conferenceroomreservationsystem.dto.PersonDTO;
import pl.sdaacademy.conferenceroomreservationsystem.entity.PersonEntity;

@RestController
@RequestMapping("/api/users")
public class PersonController extends AbstractCRUDLController<PersonEntity, PersonDTO> {
    public PersonController(AbstractCRUDLApi<PersonEntity, PersonDTO> api) {
        super(api);
    }
}

