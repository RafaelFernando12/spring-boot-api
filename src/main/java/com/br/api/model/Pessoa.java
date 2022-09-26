package com.br.api.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.br.api.dto.PessoaDto;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity()
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="ds_nome")
	private String nome;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name="dt_nascimento")
	private LocalDate nascimento;
	
	@Column(name = "ds_cpf")
	private String cpf;
	
	@Column(name="ds_nome_mae")
	private String nomeMae;
	
	public Pessoa(PessoaDto dto) {
		this.id = dto.getId();
		this.nome = dto.getNome();
		this.nascimento = dto.getNascimento();
		this.cpf = dto.getCpf();
		this.nomeMae = dto.getNomeMae();
	}
}
