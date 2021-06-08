package br.org.serratec.backend.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.backend.exception.CpfException;
import br.org.serratec.backend.exception.DataException;
import br.org.serratec.backend.exception.LimiteDependenteException;
import br.org.serratec.backend.model.Funcionario;
import br.org.serratec.backend.repository.FuncionarioRepository;
import br.org.serratec.backend.service.FuncionarioService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
	@Autowired
	private FuncionarioService funcionarioService;
	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Adicionar um Funcionario", notes = "Adiciona Funcionario")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Funcionario adicionado com sucesso"),
			@ApiResponse(code = 401, message = "Erro autenticação"), @ApiResponse(code = 403, message = "Proibido"),
			@ApiResponse(code = 404, message = "Recurso indisponivel"),
			@ApiResponse(code = 500, message = "Erro interno no Servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	public ResponseEntity<Object> adicionar(@Valid @RequestBody Funcionario funcionario) {
		try {
			Funcionario f1 = funcionarioService.inserir(funcionario);
			return ResponseEntity.ok(f1);
		} catch (CpfException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		} catch (LimiteDependenteException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		} catch (DataException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}

	@GetMapping
	@ApiOperation(value = "Listar Funcionarios", notes = "Listar Funcionarios")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Funcionarios listados com sucesso"),
			@ApiResponse(code = 401, message = "Erro autenticação"), @ApiResponse(code = 403, message = "Proibido"),
			@ApiResponse(code = 404, message = "Recurso indisponivel"),
			@ApiResponse(code = 500, message = "Erro interno no Servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	public ResponseEntity<List<Funcionario>> listar() {
		List<Funcionario> funcionarios = funcionarioRepository.findAll();
		return ResponseEntity.ok(funcionarios);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = " Buscar um Funcionario", notes = "Busca um Funcionario")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Funcionario buscado com sucesso"),
			@ApiResponse(code = 401, message = "Erro autenticação"), @ApiResponse(code = 403, message = "Proibido"),
			@ApiResponse(code = 404, message = "Recurso indisponivel"),
			@ApiResponse(code = 500, message = "Erro interno no Servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	public ResponseEntity<Funcionario> buscar(@PathVariable Long id) {
		Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
		if (funcionario.isPresent()) {
			return ResponseEntity.ok(funcionario.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Atualizar um Funcionario", notes = "Atualizo Funcionario")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Funcionario atualizado com sucesso"),
			@ApiResponse(code = 401, message = "Erro autenticação"), @ApiResponse(code = 403, message = "Proibido"),
			@ApiResponse(code = 404, message = "Recurso indisponivel"),
			@ApiResponse(code = 500, message = "Erro interno no Servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	public ResponseEntity<Funcionario> atualizar(@PathVariable Long id, @Valid @RequestBody Funcionario funcionario) {
		if (!funcionarioRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		funcionario.setId(id);
		funcionario = funcionarioRepository.save(funcionario);
		return ResponseEntity.ok(funcionario);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Apagar um Funcionario", notes = "Apagado Funcionario")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Funcionario apagado com sucesso"),
			@ApiResponse(code = 401, message = "Erro autenticação"), @ApiResponse(code = 403, message = "Proibido"),
			@ApiResponse(code = 404, message = "Recurso indisponivel"),
			@ApiResponse(code = 500, message = "Erro interno no Servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		if (!funcionarioRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}

		funcionarioRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
