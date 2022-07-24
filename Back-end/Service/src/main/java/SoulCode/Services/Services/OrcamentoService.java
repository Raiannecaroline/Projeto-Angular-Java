package SoulCode.Services.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SoulCode.Services.Models.Orcamento;
import SoulCode.Services.Models.Servico;
import SoulCode.Services.Models.StatusOrcamento;
import SoulCode.Services.Models.StatusServico;
import SoulCode.Services.Repositories.OrcamentoRepository;
import SoulCode.Services.Repositories.ServicoRepository;

@Service
public class OrcamentoService {
	
	@Autowired
	OrcamentoRepository orcamentoRepository;
	
	@Autowired
	ServicoRepository servicoRepository;
	
	/**
	 * Método para trazer todos os orçamentos cadastrados
	 * do Database (findAll)
	 */
	public List<Orcamento> mostrarTodosOrcamentos(){
		return orcamentoRepository.findAll();
	}
	
	/**
	 * Método para trazer o Orçamento pelo ID
	 */
	public Orcamento mostrarUmOrcamento(Integer idOrcamento){
		Optional<Orcamento> orcamento = orcamentoRepository.findById(idOrcamento);
		return orcamento.orElseThrow();
	}
	
	/**
	 * Método para pegar o status do Orçamento
	 * @param status
	 * @return
	 */
	public List<Orcamento> mostrarOrcamentosPeloStatus(String status){
		return orcamentoRepository.findByStatus(status);
	}
	
	public Orcamento inserirOrcamento(Orcamento orcamento, Integer idServico) {
		orcamento.setIdOrcamento(idServico);
		orcamento.setStatus(StatusOrcamento.EMITIDO);
		orcamentoRepository.save(orcamento);
		Servico servico = servicoRepository.getById(idServico);
		servico.setStatus(StatusServico.RECEBIDO);
		servico.setOrcamento(orcamento);
		servicoRepository.save(servico);
		return orcamento;
	}
	
	/**
	 * Serviço de pagamento de um orçamento (liquidar um serviço)
	 * Modificar o status do orçamento para quitado
	 */
	
	public Orcamento quitarOrcamento(Integer idOrcamento) {
		Orcamento orcamento = mostrarUmOrcamento(idOrcamento);
		orcamento.setStatus(StatusOrcamento.QUITADO);
		return orcamentoRepository.save(orcamento);
	}
	
	/**
	 * Serviço para deletar o pagamento
	 */
	public void excluirOrcamento(Integer idOrcamento) {
		Servico servico = servicoRepository.getById(idOrcamento);
		servico.setOrcamento(null);
		servico.setStatus(StatusServico.ARQUIVADO);
		servicoRepository.save(servico);
		orcamentoRepository.deleteById(idOrcamento);
	}
	
	/**
	 * Serviço para a alteração dos dados do orçamento
	 */
	public Orcamento editarOrcamento(Orcamento orcamento, Integer idOrcamento) {
		mostrarUmOrcamento(orcamento.getIdOrcamento());
		return orcamentoRepository.save(orcamento);
	}

}
