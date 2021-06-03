package br.com.venturus.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.venturus.api.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	Page<Cliente> findAllByNomeContainingIgnoreCase(String nome, Pageable pageable);
	 
	Cliente findByNome(String nome);
}
