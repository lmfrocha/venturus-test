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

import br.com.venturus.api.domain.dto.ServicoDTO;
import br.com.venturus.api.domain.dto.ServicoListDTO;
import br.com.venturus.api.repository.ServicoRepository;
import br.com.venturus.api.service.ServicoService;

@RestController
@RequestMapping("/api/v1/servico")
public class ServicoResource {

	@Autowired
	private ServicoService servicoService;
	
	@Autowired
	private ServicoRepository servicoRepository;
	
	@PostMapping
	private ResponseEntity<?> save(@RequestBody ServicoDTO servicoDTO){
		servicoService.save(servicoDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(servicoDTO);
	}
	
	@GetMapping("/id/{id}")
	private ResponseEntity<?> getById(@PathVariable Long id){
		var servicoDTO = servicoService.findBy(id);
		return Objects.nonNull(servicoDTO) ? ResponseEntity.status(HttpStatus.OK).body(servicoDTO) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/id/{id}")
	private ResponseEntity<?> deleteById(@PathVariable Long id){
		servicoRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/id/{id}")
	private ResponseEntity<?> update(@PathVariable Long id, @RequestBody ServicoDTO servicoDTO) {
		try {
			var oldProduct = servicoService.update(id, servicoDTO);
			return ResponseEntity.ok(oldProduct);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping
	private ResponseEntity<?> getAll(@RequestParam("nome") String nome, Pageable pageable){
		var response = servicoService.findAll(nome, pageable);
		return response.getServicos().getSize() > 0 ? ResponseEntity.ok(response) : ResponseEntity.noContent().build();
	}
	
}
