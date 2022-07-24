package SoulCode.Services.Controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import SoulCode.Services.Models.Funcionario;
import SoulCode.Services.Services.FuncionarioService;

@CrossOrigin
@RestController
@RequestMapping("servicos")
public class FuncionarioController {

	@Autowired
	FuncionarioService funcionarioService;
	
	@GetMapping("/funcionario")
	public List<Funcionario> mostrarTodosFuncionarios(){
		List<Funcionario>funcionarios = funcionarioService.mostrarTodosFuncionarios();
		return funcionarios;
	}
	
	@GetMapping("/funcionario/{idFuncionario}")
	public ResponseEntity<Funcionario> mostrarUmFuncionario(@PathVariable Integer idFuncionario){
		Funcionario funcionario = funcionarioService.mostrarUmFuncionario(idFuncionario);
		return ResponseEntity.ok().body(funcionario);
	}
	
	@GetMapping("/funcionario/email/{email}")
	public ResponseEntity<Funcionario> mostrarFuncionarioPeloEmail(@PathVariable String email){
		Funcionario funcionario = funcionarioService.mostrarFuncionarioPeloEmail(email);
		return ResponseEntity.ok().body(funcionario);
	}
	
	@GetMapping("/funcionarioNomeEmail/{nome}/{email}")
	public ResponseEntity<Funcionario> mostrarFuncionarioPeloNomeEEmail(@PathVariable String nome,
			                 @PathVariable String email){
		Funcionario funcionario = funcionarioService.mostrarFuncionarioPeloNomeEEmail(nome, email);
		return ResponseEntity.ok().body(funcionario);
	}
	
	/**
	 * Na linha 62 o novo usuário é salvo no banco de dados e criado o seu id.
	 * @param Funcionario
	 * @return
	 */
	@PostMapping("/funcionario")
	public ResponseEntity<Funcionario> inserirFuncionario(@RequestBody Funcionario Funcionario){
		Funcionario = funcionarioService.inserirFuncionario(Funcionario);
		URI novaUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(Funcionario.getIdFuncionario()).toUri();
		return ResponseEntity.created(novaUri).body(Funcionario);
	}
	
	@PutMapping("/funcionario/{idFuncionario}")
	public ResponseEntity<Funcionario> editarFuncionario(@PathVariable Integer idFuncionario, 
			                         @RequestBody Funcionario funcionario){
		
		funcionario.setIdFuncionario(idFuncionario);
		funcionario = funcionarioService.editarFuncionario(funcionario);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("funcionario/{idFuncionario}")
	public ResponseEntity<Void> excluirFuncionario(@PathVariable Integer idFuncionario){
		funcionarioService.excluirFuncionario(idFuncionario);
		return ResponseEntity.noContent().build();
	}
	
}
