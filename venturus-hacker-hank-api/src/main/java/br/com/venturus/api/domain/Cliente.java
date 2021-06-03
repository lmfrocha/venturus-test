package br.com.venturus.api.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

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
