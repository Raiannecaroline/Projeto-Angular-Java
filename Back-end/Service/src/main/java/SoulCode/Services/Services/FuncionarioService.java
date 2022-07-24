package SoulCode.Services.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import SoulCode.Services.Models.Funcionario;
import SoulCode.Services.Repositories.FuncionarioRepositorie;

@Service
public class FuncionarioService {
	
	//injeção de depedência (chamar uma classe ou interface e instanciar)
	@Autowired
	FuncionarioRepositorie funcRepos; //funcRepos pode ser qualquer nome (chamar o mesmo dentro do return)
	
	//serviços para buscar todos os funcionários cadastrados
	public List<Funcionario> mostrarTodosFuncionarios() {
		return funcRepos.findAll();
	}
	
	/**
	 * Utilizar a Função findById - Busca um funcionário específico pelo seu Id
	 * 
	 */
	
	public Funcionario mostrarUmFuncionario(Integer idFuncionario) {
		Optional<Funcionario> funcionario = funcRepos.findById(idFuncionario);
		return funcionario.orElseThrow();
		
	}
	
	/**
	 * FindByEmail
	 */
	
	public Funcionario mostrarFuncionarioPeloEmail(String email) {
		Optional<Funcionario> funcionario = funcRepos.findByEmail(email);
		return funcionario.orElseThrow();
	}
	
	/**
	 * FindByNomeAndEmail
	 */
	public Funcionario mostrarFuncionarioPeloNomeEEmail(String nome, String email) {
		Optional<Funcionario> funcionario = funcRepos.findByNomeAndEmail(nome, email);
		return funcionario.orElseThrow();
	}
	
	/**
	 * Save - Insere um novo registro na tabela do nosso DB
	 * por precaução vamos limpar o campo de id do Funcionario
	 */
	
	public Funcionario inserirFuncionario(Funcionario funcionario) {
		funcionario.setIdFuncionario(null);
		return funcRepos.save(funcionario);
	}
	
	/**
	 * Editar um funcionário Cadastrado
	 */
	
	public Funcionario editarFuncionario(Funcionario funcionario) {
		mostrarUmFuncionario(funcionario.getIdFuncionario());
		return funcRepos.save(funcionario);
	}
	
	
	/**
	 * Delete by id - Exlcuir um funcionário pelo seu ID
	 */
	
	public void excluirFuncionario(Integer idFuncionario) {
		mostrarUmFuncionario(idFuncionario);
		funcRepos.deleteById(idFuncionario);
	}
	
	public Funcionario salvarFoto(Integer idFuncionario, String caminhoFoto) {
		Funcionario funcionario = mostrarUmFuncionario(idFuncionario);
		funcionario.setFoto(caminhoFoto);
		return funcRepos.save(funcionario);
	}
	
}
