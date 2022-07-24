package SoulCode.Services.Controllers;

import org.hibernate.jdbc.Expectations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import SoulCode.Services.Services.FuncionarioService;
import SoulCode.Services.Utils.UploudFile;

@CrossOrigin
@RequestMapping("servicos")
@RestController
public class UploudFileController {
	
	@Autowired
	FuncionarioService funcionarioService;
	
	@PostMapping("/funcionario/envioFoto/{idFuncionario}")
	public ResponseEntity<Void> enviarDados(
			@PathVariable Integer idFuncionario, MultipartFile foto, 
			@RequestParam("nome") String nome){
		String fileName = nome;
		String uploudDir = "C:/Users/Raaia/OneDrive/Área de Trabalho/Raiane/Estudar/SoulCode/Semana 12/Aula 02/Projeto Front/Projeto-Angular-Java/Front-end-Angular/src/assets/imagens";
		String nomeMaisCaminho = "/assets/imagens/" + nome;
		
		funcionarioService.salvarFoto(idFuncionario, nomeMaisCaminho);
		
		try {
			UploudFile.salvarArquivo(uploudDir, fileName, foto);
		} catch(Exception e) {
			System.out.println("O arquivo não foi enviado: " + e);
		}
			System.out.println("Arquivo enviado: " + nomeMaisCaminho);
		
			return ResponseEntity.ok().build();
	}

}
