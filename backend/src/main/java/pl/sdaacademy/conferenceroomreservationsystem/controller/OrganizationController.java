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

    @Autowired
    public OrganizationController(AbstractCRUDLApi<OrganizationEntity, OrganizationDTO> api) {
        super(api);
    }

//    @GetMapping
//    ResponseEntity<List<OrganizationDTO>> getOrganizations() {
//        List<OrganizationDTO> organization = organizationService.getOrganizations()
//                .stream()
//                .map(org -> new OrganizationDTO(
//                        org.getName(),
//                        org.getOrganizationLeader()
//                ))
//                .collect(Collectors.toList());
//        return new ResponseEntity<>(organization, HttpStatus.OK);
//    }

//    @GetMapping(path = "/{name}")
//    ResponseEntity<OrganizationDTO> getOrganization(@NonNull @PathVariable String name) {
//        OrganizationEntity organization = organizationService.getOrganizationByName(name);
//        OrganizationDTO organizationDTO = new OrganizationDTO();
//        organizationDTO.setName(organization.getName());
//        organizationDTO.setOrganizationLeader(organization.getOrganizationLeader());
//        organizationDTO.setConferenceRooms(organization.getConferenceRooms());
//        return new ResponseEntity<>(organizationDTO, HttpStatus.OK);
//    }
//
//    @PostMapping
//    ResponseEntity<OrganizationDTO> addOrganization(@RequestBody OrganizationDTO organizationDTO) { // takes json data (raw in postman)
//        OrganizationEntity organization = new OrganizationEntity();
//        organization.setName(organizationDTO.getName());
//        organization.setOrganizationLeader(organizationDTO.getOrganizationLeader());
//        organizationService.addOrganization(organization);
//        return new ResponseEntity<>(organizationDTO, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{name}")
//    ResponseEntity<String> removeOrganization(@NonNull @PathVariable("name") String name) {
//        organizationService.removeOrganization(name);
//        return new ResponseEntity<>("Removed the organization: " + name, HttpStatus.OK);
//    }
//
//    @PutMapping
//    ResponseEntity<String> updateOrganization(@NonNull @RequestParam("old-org") String old_org, @NonNull @RequestParam("new-org") String new_org) {
//        organizationService.updateOrganization(old_org, new_org);
//        return new ResponseEntity<>(String.format("Updated organization: %s to %s", old_org, new_org), HttpStatus.OK);
//    }
}
