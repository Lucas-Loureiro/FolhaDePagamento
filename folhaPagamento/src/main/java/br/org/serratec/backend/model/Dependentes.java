package br.org.serratec.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Dependentes extends Pessoa{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_dependente")
	private Long id;
	@Enumerated(EnumType.STRING)
	private Parentesco parentesco;
	
	@ManyToOne
	@JoinColumn(name = "id_funcionario")
	private Funcionario funcionario;
	
	public Dependentes() {
		// TODO Auto-generated constructor stub
	}

	public Dependentes(Long id, Parentesco parentesco) {
		super();
		this.id = id;
		this.parentesco = parentesco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Parentesco getParentesco() {
		return parentesco;
	}

	public void setParentesco(Parentesco parentesco) {
		this.parentesco = parentesco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((parentesco == null) ? 0 : parentesco.hashCode());
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
		Dependentes other = (Dependentes) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (parentesco != other.parentesco)
			return false;
		return true;
	}
	
	
	
	

}
