package pl.sdaacademy.conferenceroomreservationsystem.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import pl.sdaacademy.conferenceroomreservationsystem.converter.OrganizationDTOConverter;
import pl.sdaacademy.conferenceroomreservationsystem.entity.OrganizationEntity;
import pl.sdaacademy.conferenceroomreservationsystem.service.OrganizationService;
import pl.sdaacademy.conferenceroomreservationsystem.session.SessionRegistry;
import pl.sdaacademy.conferenceroomreservationsystem.user.CurrentUserService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
//@TestPropertySource(locations = "classpath:application.properties")
@WebMvcTest(OrganizationController.class)
class TestOrganizationController {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrganizationService organizationService;

    @Test
    public void shouldGetAllOrganizations() throws Exception {
        OrganizationEntity org1 = new OrganizationEntity("Google");
        OrganizationEntity org2 = new OrganizationEntity("IBM");
        List<OrganizationEntity> organizationList = new ArrayList<>();
        organizationList.add(org1);
        organizationList.add(org2);
//        when(organizationService.list()).thenReturn(organizationList);

//        this.mockMvc.perform(get("/organizations/")).andDo(print()).andExpect(status().isOk())
//                .andExpect(content().string(containsString("[{\"id\":1,\"name\":\"Google\",\"organizationLeader\":null},{\"id\":2,\"name\":\"IBM\",\"organizationLeader\":null}]")));
    }
//
    @Test
    public void shouldGetOrganizationById() throws Exception {
        OrganizationDTOConverter converter = new OrganizationDTOConverter();
        OrganizationEntity org = new OrganizationEntity("Google");
        org.setId(1);
        when(organizationService.getById(1)).thenReturn(converter.convert(org));

        this.mockMvc.perform(get("/organizations/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Google")));
    }
}
