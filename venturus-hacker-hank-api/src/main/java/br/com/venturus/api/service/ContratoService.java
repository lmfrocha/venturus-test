package br.com.venturus.api.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.venturus.api.domain.Contrato;
import br.com.venturus.api.domain.dto.ContratoDTO;
import br.com.venturus.api.domain.dto.ContratoListDTO;
import br.com.venturus.api.repository.ContratoRepository;

@Service
public class ContratoService {

	@Autowired
	private ContratoRepository contratoRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public void save(ContratoDTO contratoDTO) {
		Contrato contrato = modelMapper.map(contratoDTO, Contrato.class);
		contratoRepository.save(contrato);
		contratoDTO.setId(contrato.getId());
	}

	public ContratoDTO findBy(Long id) {
		Optional<Contrato> optional = contratoRepository.findById(id);
		return optional.isPresent() ? modelMapper.map(optional.get(), ContratoDTO.class) : null;
	}

	public ContratoDTO update(Long id, ContratoDTO contratoDTO) {
		Contrato old = contratoRepository.findById(id).get();
		BeanUtils.copyProperties(contratoDTO, old, "id");
		contratoRepository.save(old);
		return modelMapper.map(old, ContratoDTO.class);
	}

	public ContratoListDTO findAll(String cnpj, Pageable pageable) {
		ContratoListDTO result = ContratoListDTO.builder().contratos(contratoRepository.findAll(cnpj, pageable)).build();
		return result;
	}
	
}
