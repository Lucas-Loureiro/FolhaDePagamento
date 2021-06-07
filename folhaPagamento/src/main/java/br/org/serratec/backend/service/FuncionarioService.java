package br.org.serratec.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.backend.exception.CpfException;
import br.org.serratec.backend.interfaces.Abatimento;
import br.org.serratec.backend.model.Dependente;
import br.org.serratec.backend.model.Funcionario;
import br.org.serratec.backend.repository.FuncionarioRepository;

@Service
public class FuncionarioService implements Abatimento{
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	public void calculoInss(Funcionario funcionario) {
		if (funcionario.getSalarioBruto() <= 1100.0 && funcionario.getSalarioBruto() > 0) {
			funcionario.setDescontoInss(funcionario.getSalarioBruto() * ALINSS1);
		} else if (funcionario.getSalarioBruto() <= 2203.48) {
			funcionario.setDescontoInss((funcionario.getSalarioBruto() * ALINSS2) - PDINSS2);
		} else if (funcionario.getSalarioBruto() <= 3305.22) {
			funcionario.setDescontoInss((funcionario.getSalarioBruto() * ALINSS3) - PDINSS3);
		} else if (funcionario.getSalarioBruto() <= 6433.57) {
			funcionario.setDescontoInss((funcionario.getSalarioBruto() * ALINSS4) - PDINSS4);
		} else {
			funcionario.setDescontoInss(( 6433.57 * ALINSS4) - PDINSS4);
		}

	}
	
	public void calculoIR(Funcionario funcionario) {
		List<Dependente>dependentes = new ArrayList<>();
		dependentes = funcionario.getDependentes();
		int dependente = 0;
		if (dependentes == null) {
			dependente = 0;
		} else {
			dependente = dependentes.size();
		}
		Double salarioTemp = funcionario.getSalarioBruto() - funcionario.getDescontoInss() - dependente * ABATIMENTO_DEPENDENTE;
		if (salarioTemp <= 1903.98 && salarioTemp > 0) {
			funcionario.setDescontoIR(0.0);
		} else if (salarioTemp <= 2826.65) {
			funcionario.setDescontoIR (((salarioTemp) * ALIR1) - PDIR1);
		} else if (salarioTemp <= 3751.05) {
			funcionario.setDescontoIR (((salarioTemp) * ALIR2) - PDIR2);
		} else if (salarioTemp <= 4664.68) {
			funcionario.setDescontoIR (((salarioTemp) * ALIR3) - PDIR3);
		} else {
			funcionario.setDescontoIR (((salarioTemp) * ALIR4) - PDIR4);
		}
	}
	
	public Double calculoSL(Funcionario funcionario) {
		return funcionario.getSalarioBruto() - funcionario.getDescontoIR() - funcionario.getDescontoInss();
	}

	public Funcionario inserir(Funcionario funcionario) throws CpfException{
		Funcionario f = funcionarioRepository.findByCpf(funcionario.getCpf());
		if(f != null) {
			throw new CpfException("Cpf ja existente");
		}
		calculoInss(funcionario);
		calculoIR(funcionario);
		
		Funcionario f1 = new Funcionario();
		
		
		f1.setNome(funcionario.getNome());
		f1.setSalarioBruto(funcionario.getSalarioBruto());
		f1.setCpf(funcionario.getCpf());
		f1.setDataNasc(funcionario.getDataNasc());
		f1.setDependentes(funcionario.getDependentes());
		f1.setDescontoInss(funcionario.getDescontoInss());
		f1.setDescontoIR(funcionario.getDescontoIR());
		f1.setSalarioLiquido(calculoSL(funcionario));
		f1 = funcionarioRepository.save(f1);
		return f1;
		
	}

}
