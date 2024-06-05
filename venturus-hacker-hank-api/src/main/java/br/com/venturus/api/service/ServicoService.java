package br.com.venturus.api.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.venturus.api.domain.Servico;
import br.com.venturus.api.domain.dto.ServicoDTO;
import br.com.venturus.api.domain.dto.ServicoListDTO;
import br.com.venturus.api.repository.ServicoRepository;

@Service
public class ServicoService {

  @Autowired
  private ServicoRepository servicoRepository;

  @Autowired
  private ModelMapper modelMapper;

  public void save(ServicoDTO servicoDTO) {
    var servico = modelMapper.map(servicoDTO, Servico.class);
    servicoRepository.save(servico);
    servicoDTO.setId(servico.getId());
  }

  public ServicoDTO findBy(Long id) {
    Optional<Servico> optional = servicoRepository.findById(id);
    return optional.isPresent() ? modelMapper.map(optional.get(), ServicoDTO.class) : null;
  }

  public ServicoDTO update(Long id, ServicoDTO servicoDTO) {
    var old = servicoRepository.findById(id).get();
    BeanUtils.copyProperties(servicoDTO, old, "id");
    servicoRepository.save(old);
    return modelMapper.map(old, ServicoDTO.class);
  }

  public ServicoListDTO findAll(String nome, Pageable pageable) {
    return ServicoListDTO.builder()
        .servicos(servicoRepository.findAllByDescricaoContainingIgnoreCase(nome, pageable)).build();
  }

}
