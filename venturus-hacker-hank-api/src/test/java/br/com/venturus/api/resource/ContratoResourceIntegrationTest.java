package br.com.venturus.api.resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.venturus.api.domain.Cliente;
import br.com.venturus.api.domain.Contrato;
import br.com.venturus.api.domain.Servico;
import br.com.venturus.api.repository.ClienteRepository;
import br.com.venturus.api.repository.ContratoRepository;
import br.com.venturus.api.repository.ServicoRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ContratoResourceIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ContratoRepository contratoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ServicoRepository servicoRepository;

	private Cliente cliente;
	
	private Servico servico;
	
	private Contrato contrato;
	
	@BeforeEach
	void setUp() {
		cliente = clienteRepository.findById(1L).get();
		servico = servicoRepository.findById(1L).get();
		contrato = new Contrato();
		contrato.setCliente(cliente);
		contrato.setServico(servico);
		contrato.setDataInicio(LocalDate.now());
		contrato.setDataFim(LocalDate.now().plusDays(10));
		contratoRepository.save(contrato);
	}
	
	@AfterEach
	void clean() {
		contratoRepository.delete(contrato);
	}
	
	@Test
	void criar_contrato_203() throws Exception {

		mockMvc.perform(post("/api/v1/contrato").contentType("application/json")
				.content(objectMapper.writeValueAsString(contrato))).andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
				.andExpect(MockMvcResultMatchers.jsonPath("$.cliente.id").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.servico.id").value(1));
	}

	@Test
	void atualizar_contrato_200() throws Exception {
		contrato.getCliente().setId(3L);
		contrato.getServico().setId(13L);
		contrato.setDataInicio(LocalDate.now());
		contrato.setDataFim(LocalDate.now().plusDays(1));

		mockMvc.perform(put("/api/v1/contrato/id/{id}", 1).contentType("application/json")
				.content(objectMapper.writeValueAsString(contrato))).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
				.andExpect(MockMvcResultMatchers.jsonPath("$.cliente.id").value(3))
				.andExpect(MockMvcResultMatchers.jsonPath("$.servico.id").value(13));

	}
	
	@Test
	void deletar_contrato_204() throws Exception {
		Long id = contrato.getId();
		mockMvc.perform(delete("/api/v1/contrato/id/{id}", id).contentType("application/json")
				.content(objectMapper.writeValueAsString(contrato))).andExpect(status().isNoContent());
	}

}
