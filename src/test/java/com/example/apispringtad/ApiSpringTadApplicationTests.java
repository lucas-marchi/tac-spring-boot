package com.example.apispringtad;

import com.example.apispringtad.dto.LoginUserDTO;
import com.example.apispringtad.model.Atuador;
import com.example.apispringtad.service.AtuadorService;
import com.example.apispringtad.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ApiSpringTadApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserService userService;

    @Test
    void shouldCreateAtuadorWhenValidRequest() throws Exception {
        LoginUserDTO loginUserDTO = new LoginUserDTO("teste@test.com", "123456789");
        String token = userService.authenticateUser(loginUserDTO).token();

        Atuador atuador = new Atuador(9L, "Atuador 2", false);
        mockMvc.perform(post("/atuador")
                        .contentType("application/json")
                        .header("Authorization", "Bearer " + token)
                        .content(objectMapper.writeValueAsString(atuador)))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldNotCreateAtuadorWhenTokenIsMissing() {
        Atuador atuador = new Atuador(9L, "Atuador 1", false);
        Exception exception = assertThrows(RuntimeException.class, () -> {
            mockMvc.perform(post("/atuador")
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(atuador)));
        });

        String expectedMessage = "O token está ausente.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldNotCreateAtuadorWhenNameIsMissing() throws Exception {
        LoginUserDTO loginUserDTO = new LoginUserDTO("teste@test.com", "123456789");
        String token = userService.authenticateUser(loginUserDTO).token();

        Atuador atuador = new Atuador(9L, null, false);
        mockMvc.perform(post("/atuador")
                        .contentType("application/json")
                        .header("Authorization", "Bearer " + token)
                        .content(objectMapper.writeValueAsString(atuador)))
                .andExpect(status().isBadRequest());
    }

	@Test
	void shouldNotGetAtuadorWhenTokenIsMissing() {
		Exception exception = assertThrows(RuntimeException.class, () -> {
			mockMvc.perform(get("/atuador")
					.contentType("application/json"));
		});

		String expectedMessage = "O token está ausente.";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void shouldNotDeleteAtuadorWhenTokenIsMissing() {
		Exception exception = assertThrows(RuntimeException.class, () -> {
			mockMvc.perform(delete("/atuador/1")
					.contentType("application/json"));
		});

		String expectedMessage = "O token está ausente.";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
}
