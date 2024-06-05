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

import br.com.venturus.api.domain.dto.ClientListDTO;
import br.com.venturus.api.domain.dto.ClienteDTO;
import br.com.venturus.api.repository.ClienteRepository;
import br.com.venturus.api.service.ClienteService;

@RestController
@RequestMapping("/api/v1/cliente")
public class ClienteResource {

  @Autowired
  private ClienteService clienteService;

  @Autowired
  private ClienteRepository clienteRepository;

  @PostMapping
  private ResponseEntity<?> save(@RequestBody ClienteDTO clienteDTO) {
    clienteService.save(clienteDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(clienteDTO);
  }

  @GetMapping("/id/{id}")
  private ResponseEntity<?> getById(@PathVariable Long id) {
    var clienteDTO = clienteService.findBy(id);
    return Objects.nonNull(clienteDTO) ? ResponseEntity.status(HttpStatus.OK).body(clienteDTO)
        : ResponseEntity.notFound().build();
  }

  @DeleteMapping("/id/{id}")
  private ResponseEntity<?> deleteById(@PathVariable Long id) {
    clienteRepository.deleteById(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/id/{id}")
  private ResponseEntity<?> update(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
    try {
      return ResponseEntity.ok(clienteService.update(id, clienteDTO));
    } catch (IllegalArgumentException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping
  private ResponseEntity<?> getAll(@RequestParam("nome") String nome, Pageable pageable) {
    ClientListDTO response = clienteService.findAll(nome, pageable);
    return response.getClientes().getSize() > 0 ? ResponseEntity.ok(response)
        : ResponseEntity.noContent().build();
  }

}
