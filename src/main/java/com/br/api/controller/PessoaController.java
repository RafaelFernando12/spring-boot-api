package com.br.api.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.api.dto.PessoaDto;
import com.br.api.repository.PessoaRepository;
import com.br.api.service.PessoaService;

import lombok.Delegate;

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
		
	@GetMapping()
	ResponseEntity<List<PessoaDto>> listar(){
		return ResponseEntity.ok(pessoaService.listar());
	}
	
	@GetMapping("/listar/paginado")
	ResponseEntity<Page<PessoaDto>> listarPaginado(Pageable page){
		return ResponseEntity.ok(pessoaService.listarPaginado(page));
	}
	
	@PutMapping()
	ResponseEntity<PessoaDto> atualiza(@RequestBody PessoaDto pessoaDto){
		return ResponseEntity.ok(pessoaService.atualizar(pessoaDto));
	}
	
	@DeleteMapping("/{id}")
	ResponseEntity<Long> deletar(@PathVariable Long id){
		return ResponseEntity.ok(pessoaService.deletar(id));
	}
}
