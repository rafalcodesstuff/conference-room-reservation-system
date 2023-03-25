package pl.sdaacademy.conferenceroomreservationsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.sdaacademy.conferenceroomreservationsystem.service.OrganizationService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@WebMvcTest(OrganizationController.class)
class TestOrganizationController {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrganizationService organizationService;

//    @Test
//    public void shouldGetAllOrganizations() throws Exception {
//        OrganizationEntity org1 = new OrganizationEntity(1, "Google");
//        OrganizationEntity org2 = new OrganizationEntity(2, "IBM");
//        List<OrganizationEntity> organizationList = new ArrayList<>();
//        organizationList.add(org1);
//        organizationList.add(org2);
//        when(organizationService.getOrganizations()).thenReturn(organizationList);
//
//        this.mockMvc.perform(get("/organizations/")).andDo(print()).andExpect(status().isOk())
//                .andExpect(content().string(containsString("[{\"id\":1,\"name\":\"Google\",\"organizationLeader\":null},{\"id\":2,\"name\":\"IBM\",\"organizationLeader\":null}]")));
//    }
//
//    @Test
//    public void shouldGetOrganization() throws Exception {
//        OrganizationEntity org = new OrganizationEntity(1, "Google");
//        when(organizationService.getOrganizationByName("Google")).thenReturn(org);
//
//        this.mockMvc.perform(get("/organizations/Google")).andDo(print()).andExpect(status().isOk())
//                .andExpect(content().string(containsString("Google")));
//    }
}