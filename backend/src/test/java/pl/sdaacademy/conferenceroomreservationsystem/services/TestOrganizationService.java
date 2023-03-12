package pl.sdaacademy.conferenceroomreservationsystem.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.sdaacademy.conferenceroomreservationsystem.controllers.OrganizationController;
import pl.sdaacademy.conferenceroomreservationsystem.models.OrganizationEntity;

import static org.mockito.Mockito.when;

@WebMvcTest(OrganizationController.class)
public class TestOrganizationService {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrganizationService organizationService;

    @Test
    public void shouldgetAllOrganization() {
        OrganizationEntity org = new OrganizationEntity("Google");

        // need to finish
        organizationService.getOrganizationByName("Google");

    }
}
