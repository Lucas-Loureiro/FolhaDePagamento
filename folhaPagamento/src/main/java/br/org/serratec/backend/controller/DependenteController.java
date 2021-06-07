package br.org.serratec.backend.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.backend.exception.CpfException;
import br.org.serratec.backend.exception.DataException;
import br.org.serratec.backend.model.Dependente;
import br.org.serratec.backend.repository.DependenteRepository;
import br.org.serratec.backend.service.DependenteService;

@RestController
@RequestMapping("/dependente")
public class DependenteController {
	@Autowired
	private DependenteService dependenteService;
	@Autowired
	private DependenteRepository dependenteRepository;
	
	@PostMapping
	public ResponseEntity<Object> adicionar(@Valid @RequestBody Dependente dependente){
		try {
			Dependente f1 = dependenteService.inserir(dependente);
			return ResponseEntity.ok(f1);
		} catch (CpfException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		} catch (DataException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}
	@GetMapping
	public ResponseEntity<List<Dependente>> listar(){
		List<Dependente> dependentes = dependenteRepository.findAll();
		return ResponseEntity.ok(dependentes);
	}

}
