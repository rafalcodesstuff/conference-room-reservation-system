package pl.sdaacademy.conferenceroomreservationsystem.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/organizations")
class OrganizationController {
    private final OrganizationService organizationService;

    @Autowired
    OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping
    List<Organization> getAllOrganizations() {
        return organizationService.getOrganizations();
    }

    @PostMapping
    ResponseEntity addOrganization(@RequestBody Organization organization) { // takes json data (raw in postman)
        try {
            organizationService.addOrganization(organization);
            return ResponseEntity.ok(HttpStatus.ACCEPTED);
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Incorrect Json Data");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Element Already Exists");
        }
    }

    @DeleteMapping("/{name}")
    ResponseEntity removeOrganization(@PathVariable("name") String name) {
        try {
            organizationService.removeOrganization(name);
            return ResponseEntity.ok(HttpStatus.ACCEPTED);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Organization Not Found");
        }
    }

    //    @GetMapping("/update?{old-org}{new-org}")
    @PutMapping
    ResponseEntity updateOrganization(@RequestParam("old-org") String old_org, @RequestParam("new-org") String new_org) {
        try {
            organizationService.updateOrganization(old_org, new_org);
            return ResponseEntity.ok(HttpStatus.ACCEPTED);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Organization Not Found");
        }
    }
}
