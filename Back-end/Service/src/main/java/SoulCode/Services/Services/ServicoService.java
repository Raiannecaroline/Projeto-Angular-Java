package SoulCode.Services.Services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SoulCode.Services.Models.Funcionario;
import SoulCode.Services.Models.Servico;
import SoulCode.Services.Models.StatusServico;
import SoulCode.Services.Repositories.FuncionarioRepositorie;
import SoulCode.Services.Repositories.ServicoRepository;

@Service
public class ServicoService {

	/**
	 * Injeção de dependência - chamando o Repository de serviços 
	 */
	@Autowired
	ServicoRepository servicoRepository;
	
	/**
	 * Cada classe externa tem que colocar um Autowired
	 */
	@Autowired
	FuncionarioRepositorie funcionarioRepositorie;
	
	/**
	 * FindAll dos serviços cadastrados
	 */
	public List<Servico> mostrarTodosServicos(){
		return servicoRepository.findAll();
	}
	
	/**
	 * FindById - Busca o Serviço pelo seu Id
	 */
	public Servico mostrarUmServico(Integer idServico) {
		Optional<Servico> servico = servicoRepository.findById(idServico);
		return servico.orElseThrow();
	}
	
	/**
	 * findByFuncionario busca todos os serviços de determinado funcionario
	 */
	
	public List<Servico> buscarServicoDoFuncionario(Integer idFuncionario){
		Optional<Funcionario> funcionario = funcionarioRepositorie.findById(idFuncionario);
		return servicoRepository.findByFuncionario(funcionario);
	}
	
	/**
	 * Buscar pela Data
	 * @param dataEntrada
	 * @return
	 */
	public List<Servico> buscarServicoPelaData(Date dataEntrada){
		return servicoRepository.findByDataEntrada(dataEntrada);
	}
	
	public List<Servico> buscarServicoPorIntervaloData(Date data1, Date data2){
		return servicoRepository.findByIntervaloData(data1, data2);
	}
	
	public List<Servico> buscarServicoPeloStatus(String status){
		return servicoRepository.findByStatus(status);
	}
	
	public List<Servico> buscarServicoSemFuncionario(){
		return servicoRepository.findByIdFuncionarioNull();
	}
	
	/**
	 * Método para cadastro de um serviço
	 * no momento do cadastro do novo serviço
	 * o status tem que ficar como recebido
	 * no momento do cadastro do novo serviço o idFuncionario tem
	 * que ficar como null	 
	 */
	public Servico inserirServico(Servico servico) {
		servico.setIdServico(null);
		servico.setStatus(StatusServico.RECEBIDO);
		servico.setFuncionario(null);
		return servicoRepository.save(servico);
	}
	
	/**
	 * Método para adquirir um determinado servico para um determinado funcionario
	 */
	public Servico atribuirFuncionario(Integer idServico, Integer idFuncionario) {
		/**
		 * Buscamos o funcionario para ser atribuido ao serviço através do seu id
		 */
		Optional<Funcionario> funcionario = funcionarioRepositorie.findById(idFuncionario);
		/**
		 * Buscamos o serviço para qual vai ser esse funcionário atribuido
		 */
		Servico servico = mostrarUmServico(idServico);
		if (servico.getFuncionario() != null) {
			servico.setIdServico(idServico);
			servico.setFuncionario(funcionario.get());
			servico.setStatus(StatusServico.ATRIBUIDO);
		}
		return servicoRepository.save(servico);
	}
	
	/**
	 * Método para mudar o status do serviço para concluído
	 */
	public Servico concluirServico(Integer idServico) {
		Servico servico = mostrarUmServico(idServico);
		//servico.setIdServico(idServico);
		if (servico.getFuncionario() != null){
			servico.setStatus(StatusServico.CONCLUIDO);
		}
		
		return servicoRepository.save(servico);
	}
	
	// deleteById - excluir um serviço pela sua chave primaria
		public void deletarUmServico(Integer idServico) {
			servicoRepository.deleteById(idServico);
		}
				
	// editar os dados de um serviço
		public Servico editarServico(Servico servico, Integer idFuncionario) {
			mostrarUmServico(servico.getIdServico());
			Funcionario funcionario = funcionarioRepositorie.getById(idFuncionario);
			servico.setFuncionario(funcionario);
			return servicoRepository.save(servico);
		}
	
}
