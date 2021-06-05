package br.org.serratec.backend.model;

import java.math.BigDecimal;
import java.util.List;

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
	private BigDecimal salarioBruto;
	private BigDecimal descontoInss;
	private BigDecimal descontoIR;
	@OneToMany
	@JoinColumn(name = "id_dependente")
	private List<Dependentes> dependentes;

	public Funcionario() {
		// TODO Auto-generated constructor stub
	}

	public Funcionario(Long id, BigDecimal salarioBruto, BigDecimal descontoInss, BigDecimal descontoIR,
			List<Dependentes> dependentes) {
		super();
		this.id = id;
		this.salarioBruto = salarioBruto;
		this.descontoInss = descontoInss;
		this.descontoIR = descontoIR;
		this.dependentes = dependentes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getSalarioBruto() {
		return salarioBruto;
	}

	public void setSalarioBruto(BigDecimal salarioBruto) {
		this.salarioBruto = salarioBruto;
	}

	public BigDecimal getDescontoInss() {
		return descontoInss;
	}

	public void setDescontoInss(BigDecimal descontoInss) {
		this.descontoInss = descontoInss;
	}

	public BigDecimal getDescontoIR() {
		return descontoIR;
	}

	public void setDescontoIR(BigDecimal descontoIR) {
		this.descontoIR = descontoIR;
	}

	public List<Dependentes> getDependentes() {
		return dependentes;
	}

	public void setDependentes(List<Dependentes> dependentes) {
		this.dependentes = dependentes;
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
