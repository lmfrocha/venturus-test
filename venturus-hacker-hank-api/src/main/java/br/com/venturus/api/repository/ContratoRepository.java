package br.com.venturus.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.venturus.api.domain.Contrato;

@Repository
public interface ContratoRepository  extends JpaRepository<Contrato, Long>{
	
	
	@Query("from Contrato c "
			+ " inner join c.cliente cli "
			+ " where cli.cnpj like %:cnpj% "
			+ " order by "
			+ " cli.nome ")
	Page<Contrato> findAll(@Param(value = "cnpj") String cnpj, Pageable pageable);

	@Query("from Contrato c "
			+ " inner join c.cliente cli "
			+ " where cli.cnpj like %:cnpj% ")
	Contrato findByCnpj(@Param(value = "cnpj") String cnpj);
	
}
