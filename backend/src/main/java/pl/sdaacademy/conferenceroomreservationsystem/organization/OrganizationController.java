package pl.sdaacademy.conferenceroomreservationsystem.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    List<Organization> getAllOrganizations(@RequestParam(required = false) String name) {
        return organizationService.getOrganizations(name);
    }

    @GetMapping(path = "{id}")
    Organization getOrganizationById(@NonNull @PathVariable Integer id) {
        return organizationService.getOrganizationByID(id);
    }

    @PostMapping
    ResponseEntity<String> addOrganization(@Valid @RequestBody Organization organization) { // takes json data (raw in postman)
        try {
            organizationService.addOrganization(organization);
            return new ResponseEntity<>("Added the organization: " + organization.getName(), HttpStatus.OK);
        } catch (NullPointerException e) {
            return new ResponseEntity<>("Incorrect Json Data", HttpStatus.CONFLICT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Element Already Exists", HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/{name}")
    void removeOrganization(@PathVariable("name") String name) {
        organizationService.removeOrganization(name);
//        return new ResponseEntity<>("Removed the organization: " + name, HttpStatus.OK);
    }

    //    @GetMapping("/update?{old-org}{new-org}")
    @PutMapping
    void updateOrganization(@RequestParam("old-org") String old_org, @RequestParam("new-org") String new_org) {
        organizationService.updateOrganization(old_org, new_org);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    ResponseEntity<String> handleNoSuchElementException(NoSuchElementException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
