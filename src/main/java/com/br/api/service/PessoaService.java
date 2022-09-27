package com.br.api.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.br.api.dto.PessoaDto;
import com.br.api.model.Pessoa;
import com.br.api.repository.PessoaRepository;
import com.br.api.util.BeanUtil;


@Service
public class PessoaService {
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	public Pessoa save(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}
	
	public PessoaDto salvar(PessoaDto pessoaDto) {
	Pessoa pessoa = this.save(new Pessoa(pessoaDto));
		return new PessoaDto(pessoa);
	}
	
	public Pessoa findById(Long id) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(id);
		if(pessoa.isPresent()) {
			return pessoa.get();
		}
		return null;
	}

	public PessoaDto atualizar(PessoaDto pessoaDto) {
		Pessoa objBanco = this.findById(pessoaDto.getId());
		BeanUtils.copyProperties(pessoaDto, objBanco, BeanUtil.getNull(pessoaDto));
		objBanco = this.save(objBanco); 
		return new PessoaDto(objBanco);
	}

	public List<PessoaDto> listar() {
		List<Pessoa> pessoas = pessoaRepository.findAll();
		return pessoas.stream().map(PessoaDto :: new).toList();
	}

	public Page<PessoaDto> listarPaginado(Pageable page) {
		Page<Pessoa> pagePessoa = pessoaRepository.findAll(page);
		Page<PessoaDto> pagePessoaDto = pagePessoa.map(p -> {
			PessoaDto pessoa = new PessoaDto(p);
			return pessoa;
		});
		return pagePessoaDto;
	}

	public Long deletar(Long id) {
		Pessoa pessoa = this.findById(id);
		pessoaRepository.delete(pessoa);
		return id;
	}
}
