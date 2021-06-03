package br.com.venturus.api.domain.dto;

import java.io.Serializable;

import org.springframework.data.domain.Page;

import br.com.venturus.api.domain.Contrato;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ContratoListDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Page<Contrato> contratos;
	
}
