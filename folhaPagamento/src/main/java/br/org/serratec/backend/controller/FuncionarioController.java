package br.org.serratec.backend.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.serratec.backend.exception.CpfException;
import br.org.serratec.backend.model.Funcionario;
import br.org.serratec.backend.repository.FuncionarioRepository;
import br.org.serratec.backend.service.FuncionarioService;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
	@Autowired
	private FuncionarioService funcionarioService;
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@PostMapping
	public ResponseEntity<Object> adicionar(@RequestBody Funcionario funcionario){
		try {
			Funcionario f1 = funcionarioService.inserir(funcionario);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(f1.getId()).toUri();
			return ResponseEntity.created(uri).body(f1);
			
		} catch (CpfException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}
	@GetMapping
	public ResponseEntity<List<Funcionario>> listar(){
		List<Funcionario> funcionarios = funcionarioRepository.findAll();
		return ResponseEntity.ok(funcionarios);
	}
}
