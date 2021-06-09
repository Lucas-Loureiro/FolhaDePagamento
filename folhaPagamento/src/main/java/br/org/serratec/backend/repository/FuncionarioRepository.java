package br.org.serratec.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.backend.model.Funcionario;


public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
	public Funcionario findByCpf(String cpf);

}
