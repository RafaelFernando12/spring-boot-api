package com.br.api.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.api.dto.PessoaDto;
import com.br.api.service.PessoaService;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {
	/*
	private static PessoaService pessoaService;
	
	public PessoaController(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}
	*/
	
	@Autowired
	PessoaService pessoaService;
	
	@PostMapping()
	ResponseEntity<PessoaDto> salvar(@RequestBody PessoaDto pessoaDto){
		PessoaDto pessoa = pessoaService.salvar(pessoaDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(pessoa.getId()).toUri();
		return ResponseEntity.created(uri).body(pessoa);
	}
	
}
