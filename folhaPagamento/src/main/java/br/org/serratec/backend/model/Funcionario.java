package br.org.serratec.backend.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Funcionario extends Pessoa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_funcionario")
	private Long id;
	private Double salarioBruto;
	private Double descontoInss;
	private Double descontoIR;
	private Double salarioLiquido;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_dependente")
	private List<Dependente> dependentes;

	public Funcionario() {
		// TODO Auto-generated constructor stub
	}

	public Funcionario(Long id, Double salarioBruto, Double descontoInss, Double descontoIR, Double salarioLiquido,
			List<Dependente> dependentes) {
		super();
		this.id = id;
		this.salarioBruto = salarioBruto;
		this.descontoInss = descontoInss;
		this.descontoIR = descontoIR;
		this.salarioLiquido = salarioLiquido;
		this.dependentes = dependentes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getSalarioBruto() {
		return salarioBruto;
	}

	public void setSalarioBruto(Double salarioBruto) {
		this.salarioBruto = salarioBruto;
	}

	public Double getDescontoInss() {
		return descontoInss;
	}

	public void setDescontoInss(Double descontoInss) {
		this.descontoInss = descontoInss;
	}

	public Double getDescontoIR() {
		return descontoIR;
	}

	public void setDescontoIR(Double descontoIR) {
		this.descontoIR = descontoIR;
	}

	public List<Dependente> getDependentes() {
		return dependentes;
	}

	public void setDependentes(List<Dependente> dependentes) {
		this.dependentes = dependentes;
	}

	public Double getSalarioLiquido() {
		return salarioLiquido;
	}

	public void setSalarioLiquido(Double salarioLiquido) {
		this.salarioLiquido = salarioLiquido;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
