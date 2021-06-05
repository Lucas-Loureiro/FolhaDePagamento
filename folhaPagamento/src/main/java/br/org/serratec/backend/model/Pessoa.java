package br.org.serratec.backend.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;


@MappedSuperclass
public abstract class Pessoa {

	@NotBlank(message = "Nome não pode estar em branco")
	protected String nome;
	@CPF(message = "CPF inválido")
	@NotBlank(message = "CPF não pode estar em branco")
	protected String cpf;
	@Past(message = "Data inválida")
	@Size(max = 8, message = "Tamanho máximo de {max} caracteres")
	protected LocalDate dataNasc;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(LocalDate dataNasc) {
		this.dataNasc = dataNasc;
	}


	

}
