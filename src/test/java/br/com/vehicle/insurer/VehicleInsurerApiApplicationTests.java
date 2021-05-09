package br.com.vehicle.insurer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.vehicle.insurer.api.dto.ClientDTO;
import br.com.vehicle.insurer.api.dto.PolicyDTO;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Validação da API")
class VehicleInsurerApiApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("Listando os clientes")
	void getClients() throws Exception {
		mockMvc.perform(get("/clients"))
			.andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("Cadastrando um cliente")
	void saveClient() throws Exception {
		ClientDTO request = new ClientDTO();
		request.setFullname("Fulano");
		request.setCpf("787.624.611-76");
		request.setCity("São Paulo");
		request.setUf("SP");
		mockMvc.perform(post("/clients")
				.contentType(MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
				.content(asJsonString(request)))
				.andExpect(status().isCreated());
	}
	
	@Test
	@DisplayName("Cadastrando um cliente sem algum dado")
	void saveClientWithoutData() throws Exception {
		ClientDTO request = new ClientDTO();
		request.setFullname("Fulano");
		request.setCpf("787.624.611-76");
		request.setUf("SP");
		mockMvc.perform(post("/clients")
				.contentType(MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
				.content(asJsonString(request)))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.error", is("Erro de validação")));
	}
	
	@Test
	@DisplayName("Cadastrando um cliente com um cpf inválido")
	void saveClientWithInvalidCpf() throws Exception {
		ClientDTO request = new ClientDTO();
		request.setFullname("Fulano");
		request.setCpf("000.000.000-00");
		request.setCity("São Paulo");
		request.setUf("SP");
		mockMvc.perform(post("/clients")
				.contentType(MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
				.content(asJsonString(request)))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.error", is("Erro de validação")));
	}
	
	@Test
	@DisplayName("Listando as apólices")
	void getPolicies() throws Exception {
		mockMvc.perform(get("/policies"))
			.andExpect(status().isOk());
	}
	/*
	@Test
	@DisplayName("Cadastrando uma apólice")
	void savePolicy() throws Exception {
		PolicyDTO request = new PolicyDTO();
		request.setPolicyValue(200);
		request.setVehicleLicensePlate("NXR-0908");
		request.setStartOfTerm(LocalDateTime.now());
		request.setEndOfTerm(LocalDateTime.now());
		mockMvc.perform(post("/policies")
				.contentType(MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
				.content(asJsonStringg(request)))
				.andExpect(status().isCreated());
	}*/
	
	@Test
	@DisplayName("Cadastrando uma apólice sem algum dado")
	void savePolicyWithoutData() throws Exception {
		PolicyDTO request = new PolicyDTO();
		request.setPolicyValue(2.0);
		request.setStartOfTerm(LocalDateTime.now());
		request.setEndOfTerm(LocalDateTime.now());
		mockMvc.perform(post("/policies")
				.contentType(MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
				.content(asJsonStringg(request)))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.error", is("Bad Request")));
	}
	
	public static String asJsonString(ClientDTO request) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(request);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
	
	public static String asJsonStringg(PolicyDTO request) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(request);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

}