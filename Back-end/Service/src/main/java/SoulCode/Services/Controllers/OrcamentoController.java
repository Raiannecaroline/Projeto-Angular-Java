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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import SoulCode.Services.Models.Orcamento;
import SoulCode.Services.Services.OrcamentoService;

@CrossOrigin
@RestController
@RequestMapping("servicos")
public class OrcamentoController {

	@Autowired
	OrcamentoService orcamentoService;
	
	@GetMapping("/orcamento")
	public List<Orcamento> mostrarTodosOrcamentos(){
		List<Orcamento> orcamentos = orcamentoService.mostrarTodosOrcamentos();
		return orcamentos;
	}
	
	@GetMapping("/orcamento/{idOrcamento}")
	public ResponseEntity<Orcamento> mostrarUmOrcamento(
			@PathVariable Integer idOrcamento){
		Orcamento orcamentos = orcamentoService.mostrarUmOrcamento(idOrcamento);
		return ResponseEntity.ok().body(orcamentos);
	}
	
	@GetMapping("/orcamentoStatus")
	public List<Orcamento> mostrarOrcamentosPeloStatus(@RequestParam("status") String status){
		List<Orcamento> orcamentos = orcamentoService.mostrarOrcamentosPeloStatus(status);
		return orcamentos;
	}
	
	@PostMapping("/orcamento/{idServico}")
	public ResponseEntity<Orcamento> inserirOrcamento(
			@PathVariable Integer idServico, @RequestBody Orcamento orcamento){
		
		orcamento = orcamentoService.inserirOrcamento(orcamento, idServico);
		URI uriNova = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(orcamento.getIdOrcamento()).toUri();
		return ResponseEntity.created(uriNova).build();
	}
	
	@PostMapping("/orcamentoQuitado/{idOrcamento}")
	public ResponseEntity<Void> quitarOrcamento(
			@PathVariable Integer idOrcamento){
		Orcamento orcamento = orcamentoService.quitarOrcamento(idOrcamento);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/orcamento/{idOrcamento}")
	public ResponseEntity<Void> excluirOrcamento(
			@PathVariable Integer idOrcamento){
		orcamentoService.excluirOrcamento(idOrcamento);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/orcamento/{idOrcamento}")
	public ResponseEntity<Orcamento> editarOrcamento(
			@PathVariable Integer idOrcamento, @RequestBody Orcamento orcamento){
		orcamento.setIdOrcamento(idOrcamento);
		orcamentoService.editarOrcamento(orcamento, idOrcamento);
		return ResponseEntity.ok().body(orcamento);
	}
	
	
}
