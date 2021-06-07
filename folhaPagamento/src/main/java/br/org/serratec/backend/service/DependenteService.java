package br.org.serratec.backend.service;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.backend.exception.CpfException;
import br.org.serratec.backend.exception.DataException;
import br.org.serratec.backend.model.Dependente;
import br.org.serratec.backend.repository.DependenteRepository;

@Service
public class DependenteService {
	@Autowired
	private DependenteRepository dependenteRepository;

	public Dependente inserir(Dependente dependente) throws CpfException, DataException{
		Dependente d = dependenteRepository.findByCpf(dependente.getCpf());
		if(d != null) {
			throw new CpfException("Cpf ja existente");
		}
		else if (Period.between(dependente.getDataNasc(), LocalDate.now()).getYears() >= 18) {
			throw new DataException("Dependente e maior de idade");
		}
		Dependente d1 = new Dependente();
		d1.setCpf(dependente.getCpf());
		d1.setDataNasc(dependente.getDataNasc());
		d1.setParentesco(dependente.getParentesco());
		d1.setNome(dependente.getNome());
		d1= dependenteRepository.save(d1);
		return d1;
	}

}
