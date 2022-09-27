package com.br.api.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.api.dto.PessoaDto;
import com.br.api.model.Pessoa;
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
	
	@GetMapping("/{id}")
	ResponseEntity<PessoaDto> findById(@PathVariable Long id){
		PessoaDto pessoaDto = pessoaService.findById(id);
		if(pessoaDto != null) {
			return ResponseEntity.status(HttpStatus.OK).body(pessoaDto);
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(pessoaDto);
	}
		
	@GetMapping()
	ResponseEntity<List<PessoaDto>> listar(){
		List<PessoaDto> lista = pessoaService.listar();
		if(lista.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(lista);
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(lista);
	}
	
	@GetMapping("/listar/paginado")
	ResponseEntity<Page<PessoaDto>> listarPaginado(Pageable page){
		Page<PessoaDto> pagina = pessoaService.listarPaginado(page);
		if(pagina.getTotalElements() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(pagina);
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(pagina);
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
