package br.com.venturus.api.resource;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.venturus.api.domain.dto.ContratoDTO;
import br.com.venturus.api.domain.dto.ContratoListDTO;
import br.com.venturus.api.repository.ContratoRepository;
import br.com.venturus.api.service.ContratoService;

@RestController
@RequestMapping("/api/v1/contrato")
public class ContratoResource {

	@Autowired
	private ContratoService contratoService;
	
	@Autowired
	private ContratoRepository contratoRepository;
	
	@PostMapping
	private ResponseEntity<?> save(@RequestBody ContratoDTO contratoDTO){
		contratoService.save(contratoDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(contratoDTO);
	}
	
	@GetMapping("/id/{id}")
	private ResponseEntity<?> getById(@PathVariable Long id){
		var contratoDTO = contratoService.findBy(id);
		return Objects.nonNull(contratoDTO) ? ResponseEntity.status(HttpStatus.OK).body(contratoDTO) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/id/{id}")
	private ResponseEntity<?> deleteById(@PathVariable Long id){
		contratoRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/id/{id}")
	private ResponseEntity<?> update(@PathVariable Long id, @RequestBody ContratoDTO contratoDTO) {
		try {
			var oldProduct = contratoService.update(id, contratoDTO);
			return ResponseEntity.ok(oldProduct);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping
	private ResponseEntity<?> getAll(@RequestParam("cnpj") String cnpj,Pageable pageable){
		var response = contratoService.findAll(cnpj, pageable);
		return response.getContratos().getSize() > 0 ? ResponseEntity.ok(response) : ResponseEntity.noContent().build();
	}
	
}
