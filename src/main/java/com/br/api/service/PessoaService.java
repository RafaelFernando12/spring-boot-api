package com.br.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.api.dto.PessoaDto;
import com.br.api.model.Pessoa;
import com.br.api.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	PessoaRepository pessoaRepository;

	public PessoaDto salvar(PessoaDto pessoaDto) {
		Pessoa pessoa = new Pessoa(pessoaDto);
		pessoa = pessoaRepository.save(pessoa);
		return new PessoaDto(pessoa);
	}

}
