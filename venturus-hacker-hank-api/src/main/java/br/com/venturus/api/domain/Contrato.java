package br.com.venturus.api.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsExclude;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode.Include;

@Data
@Entity
@Table(name = "contrato")
public class Contrato {

	@Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Campo não pode ser nulo")
	@EqualsExclude
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "data_inicio")
	private LocalDate dataInicio;

	@NotNull(message = "Campo não pode ser nulo")
	@EqualsExclude
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "data_fim")
	private LocalDate dataFim;
	
	@NotNull(message = "Campo não pode ser nulo")
	@Include
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@NotNull(message = "Campo não pode ser nulo")
	@EqualsExclude
	@ManyToOne
	@JoinColumn(name = "servico_id")
	private Servico servico;
	
}
