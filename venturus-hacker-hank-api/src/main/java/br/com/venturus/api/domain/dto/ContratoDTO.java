package br.com.venturus.api.domain.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.venturus.api.domain.Cliente;
import br.com.venturus.api.domain.Servico;
import lombok.Data;

@Data
public class ContratoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataInicio;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataFim;
	private Cliente cliente;
	private Servico servico;
	
}
