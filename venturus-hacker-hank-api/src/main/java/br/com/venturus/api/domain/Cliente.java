package br.com.venturus.api.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode.Exclude;
import lombok.EqualsAndHashCode.Include;

@Data
@Entity
@Table(name = "cliente")
public class Cliente implements Serializable{
	private static final long serialVersionUID = 1L;

	@Exclude
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Campo não pode ser Nulo")
	@Exclude
	@Column(name = "nome")
	private String nome;
	
	@NotNull(message = "Campo não pode ser Nulo")
	@Include
	@Max(value = 14)
	@Column(name = "cnpj")
	private String cnpj;
	
}
