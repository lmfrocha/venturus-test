package br.com.venturus.api.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.venturus.api.domain.Cliente;
import br.com.venturus.api.domain.dto.ClientListDTO;
import br.com.venturus.api.domain.dto.ClienteDTO;
import br.com.venturus.api.repository.ClienteRepository;

@Service
public class ClienteService {

  @Autowired
  private ClienteRepository clienteRepository;

  @Autowired
  private ModelMapper modelMapper;

  public void save(ClienteDTO clienteDTO) {
    var empresa = modelMapper.map(clienteDTO, Cliente.class);
    clienteRepository.save(empresa);
    clienteDTO.setId(empresa.getId());
  }

  public ClienteDTO findBy(Long id) {
    Optional<Cliente> optional = clienteRepository.findById(id);
    return optional.isPresent() ? modelMapper.map(optional.get(), ClienteDTO.class) : null;
  }

  public ClienteDTO update(Long id, ClienteDTO clienteDTO) {
    var old = clienteRepository.findById(id).get();
    BeanUtils.copyProperties(clienteDTO, old, "id");
    clienteRepository.save(old);
    return modelMapper.map(old, ClienteDTO.class);
  }

  public ClientListDTO findAll(String nome, Pageable pageable) {
    return ClientListDTO.builder()
        .clientes(clienteRepository.findAllByNomeContainingIgnoreCase(nome, pageable)).build();
  }

}
