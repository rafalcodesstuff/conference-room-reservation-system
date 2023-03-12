package pl.sdaacademy.conferenceroomreservationsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import pl.sdaacademy.conferenceroomreservationsystem.models.OrganizationEntity;
import pl.sdaacademy.conferenceroomreservationsystem.services.OrganizationService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/organizations")
public class OrganizationController {
    private final OrganizationService organizationService;

    @Autowired
    OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping(path = "{name}")
    ResponseEntity<OrganizationEntity> getOrganizationByName(@NonNull @PathVariable String name) {
        OrganizationEntity result = organizationService.getOrganizationByName(name);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<String> addOrganization(@Valid @RequestBody OrganizationEntity organization) { // takes json data (raw in postman)
        organizationService.addOrganization(organization);
        return new ResponseEntity<>("Added the organization: " + organization.getName(), HttpStatus.OK);
    }

    @DeleteMapping("/{name}")
    ResponseEntity<String> removeOrganization(@PathVariable("name") String name) {
        organizationService.removeOrganization(name);
        return new ResponseEntity<>("Removed the organization: " + name, HttpStatus.OK);
    }

    @PutMapping
    ResponseEntity<String> updateOrganization(@RequestParam("old-org") String old_org, @RequestParam("new-org") String new_org) {
        organizationService.updateOrganization(old_org, new_org);
        return new ResponseEntity<>(String.format("Updated organization: %s to %s", old_org, new_org), HttpStatus.OK);
    }
}
