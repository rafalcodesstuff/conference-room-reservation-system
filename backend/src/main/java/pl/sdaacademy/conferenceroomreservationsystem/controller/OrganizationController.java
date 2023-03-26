package pl.sdaacademy.conferenceroomreservationsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sdaacademy.conferenceroomreservationsystem.api.AbstractCRUDLApi;
import pl.sdaacademy.conferenceroomreservationsystem.dto.OrganizationDTO;
import pl.sdaacademy.conferenceroomreservationsystem.entity.OrganizationEntity;

@RestController
@RequestMapping("/api/organizations")
public class OrganizationController extends AbstractCRUDLController<OrganizationEntity, OrganizationDTO> {
    public OrganizationController(AbstractCRUDLApi<OrganizationEntity, OrganizationDTO> api) {
        super(api);
    }
}
