package org.interaction.interactionbackend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.interaction.interactionbackend.po.User;
import org.interaction.interactionbackend.repository.UserRepository;
import org.interaction.interactionbackend.serviceimpl.events.PhotographerSelectionServiceImpl;
import org.interaction.interactionbackend.serviceimpl.UserServiceImpl;
import org.interaction.interactionbackend.util.EnvLoader;
import org.interaction.interactionbackend.util.ResponseBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.interaction.interactionbackend.enums.Role;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.HashMap;
import java.util.Map;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class UserTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userServiceImpl;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PhotographerSelectionServiceImpl photographerSelectionServiceImpl;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeAll
    public static void setUp() {
        try {
            EnvLoader.loadEnv(".env");
            System.out.println(System.getenv("DB_NAME"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    @Test
    public void testUserLogin() throws Exception {
        String email = "testuser@example.com";
        String password = "password123";
        User user = new User(email, password, Role.NORMAL);
        user.setId(1);
        // success
        when(userServiceImpl.login(anyString(), anyString())).thenReturn(null);
        Map<String, String> requestDataSuccess = new HashMap<>();
        requestDataSuccess.put("email", email);
        requestDataSuccess.put("upass", password);
        mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDataSuccess)))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.code").value(1));
        // fail
        when(userServiceImpl.login(anyString(), anyString())).thenReturn(null);
        requestDataSuccess.put("email", email);
        mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDataSuccess)))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.code").value(0));
    }

    @Deprecated
    @Test
    public void testTokenInterceptor() throws Exception {
        // fail
        mockMvc.perform(MockMvcRequestBuilders.post("/event/register")
//                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new HashMap<>())))
                        .andExpect(status().isUnauthorized());
        // success
        // get token and request
        String email = "testuser@example.com";
        String password = "password123";
        User user = new User(email, password, Role.NORMAL);
        user.setId(1);
        //first login to get token
        when(userServiceImpl.login(anyString(), anyString())).thenReturn(null);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(
                "/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                new HashMap<String, Object>(){{
                                    put("email", email);
                                    put("upass", password);
                                }})))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.code").value(1))
                        .andExpect(jsonPath("$.data").isString())
                        .andReturn();
        String token = JsonPath.read(mvcResult.getResponse().getContentAsString(),
                "$.data");
        //then use token to register event
        when(userRepository.findByEmail(email)).thenReturn(null);
        when(photographerSelectionServiceImpl.registerEvent(any(), anyString())).thenReturn(ResponseBuilder.buildSuccessResponse("success", null));
        mockMvc.perform(MockMvcRequestBuilders.post("/event/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .content(objectMapper.writeValueAsString(new HashMap<String, String>(){{
                            put("contact", "12345678901");
                            put("eventId", "1");
                        }})))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.code").value(1));
    }
}
