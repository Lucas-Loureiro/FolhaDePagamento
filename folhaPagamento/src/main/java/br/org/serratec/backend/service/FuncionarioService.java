package br.org.serratec.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.backend.exception.CpfException;
import br.org.serratec.backend.model.Funcionario;
import br.org.serratec.backend.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
	@Autowired
	private FuncionarioRepository funcionarioRepository;

	public Funcionario inserir(Funcionario funcionario) throws CpfException{
		Funcionario f = funcionarioRepository.findByCpf(funcionario.getCpf());
		if(f != null) {
			throw new CpfException("Cpf ja existente");
		}
		Funcionario f1 = new Funcionario();
		f1.setNome(funcionario.getNome());
		f1.setSalarioBruto(funcionario.getSalarioBruto());
		f1.setCpf(funcionario.getCpf());
		f1.setDataNasc(funcionario.getDataNasc());
		f1.setDependentes(funcionario.getDependentes());
		f1 = funcionarioRepository.save(f1);
		return f1;
		
	}

}
