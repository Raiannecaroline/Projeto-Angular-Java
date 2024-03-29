package SoulCode.Services.Models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Essa anotação diz ao Spring que essa class vai ser uma representação
 * de uma tabela no nosso database
 * @author Raaia
 *
 */
@Entity
public class Servico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idServico;
	
	@Column(nullable = false, length = 100)
	private String titulo;
	
	@Column(nullable = false)
	private String descricao;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(columnDefinition = "date", nullable = false)
	private Date dataEntrada;
	
	/**
	 * Esse atributo vai ser um enum - é um atributo personalizado
	 * que nós vamos criar e daremos o nome de StatusServico
	 */
	@Enumerated(EnumType.STRING)
	private StatusServico status;
	
	@ManyToOne
	@JoinColumn(name = "idFuncionario")
	private Funcionario funcionario;
	
	/**
	 * Cascade fala que as alerações feita na tabela de serviço também tem que atingir a
	 * tabela de Orçamento
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idOrcamento", unique = true)
	private Orcamento orcamento;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idCliente", unique = true)
	private DadosClientes dadosClientes;

	public Integer getIdServico() {
		return idServico;
	}

	public void setIdServico(Integer idServico) {
		this.idServico = idServico;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public StatusServico getStatus() {
		return status;
	}

	public void setStatus(StatusServico status) {
		this.status = status;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Orcamento getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
	}
	
}
