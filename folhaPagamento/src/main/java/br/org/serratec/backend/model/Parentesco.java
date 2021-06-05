package br.org.serratec.backend.model;

import com.fasterxml.jackson.annotation.JsonCreator;



public enum Parentesco {
	FILHO,
	SOBRINHO,
	OUTROS;
	
	
	/*@JsonCreator
	public static Parentesco verificar(String valor) throws EnumValidationException{
		for (Parentesco parentesco : values()) {
			if (valor.equals(parentesco.name())) {
				return parentesco;
			}
		}
		throw new EnumValidationException("Categoria Inválida !!");
	}*/
}
