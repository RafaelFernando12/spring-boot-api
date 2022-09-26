package com.br.api.dto;

import java.time.LocalDate;

import com.br.api.model.Pessoa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PessoaDto {
	private Long id;
	private String nome;
	private LocalDate nascimento;
	private String cpf;
	private String nomeMae;
	
	public PessoaDto(Pessoa model) {
		this.id = model.getId();
		this.nome = model.getNome();
		this.nascimento = model.getNascimento();
		this.cpf = model.getCpf();
		this.nomeMae = model.getNomeMae();
	}
}
