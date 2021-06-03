package br.com.venturus.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.venturus.api.domain.Servico;

@Repository
public interface ServicoRepository  extends JpaRepository<Servico, Long>{
	
	Page<Servico> findAllByDescricaoContainingIgnoreCase(String descricao, Pageable pageable);
	
	Servico findByDescricao(String descricao);
}
