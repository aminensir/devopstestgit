package com.example.testeditions;

import com.example.testeditions.Controllers.UserController;
import com.example.testeditions.Services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

@SpringBootTest
class TestEditioNsApplicationTests {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void testBanUserForOneMinute_Success() throws Exception {
        String email = "test@example.com";

        doNothing().when(userService).banUserByEmailTemporarily(email);

        mockMvc.perform(MockMvcRequestBuilders.post("/ban/temp")
                        .param("email", email)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Utilisateur banni temporairement pendant une minute."));  // Verify success message
    }

    @Test
    void testBanUserForOneMinute_Failure() throws Exception {
        String email = "test@example.com";

        doThrow(new RuntimeException("Some error")).when(userService).banUserByEmailTemporarily(email);

        mockMvc.perform(MockMvcRequestBuilders.post("/ban/temp")
                        .param("email", email)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andExpect(MockMvcResultMatchers.content().string("Une erreur s'est produite lors du bannissement de l'utilisateur : Some error"));  // Verify error message
    }

}
