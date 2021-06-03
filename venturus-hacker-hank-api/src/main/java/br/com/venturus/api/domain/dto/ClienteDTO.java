package br.com.venturus.api.domain.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ClienteDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String cnpj;
	
}
