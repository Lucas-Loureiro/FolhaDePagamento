package br.org.serratec.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.backend.model.Dependente;

public interface DependenteRepository extends JpaRepository<Dependente, Long>{
	public Dependente findByCpf(String cpf);

}
