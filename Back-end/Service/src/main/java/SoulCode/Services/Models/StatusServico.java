package SoulCode.Services.Models;

public enum StatusServico {
	
	RECEBIDO("Recebido"),
	ATRIBUIDO("Atribuido"),
	CONCLUIDO("Concluido"),
	ARQUIVADO("Arquivado");
	
	private String descricao;
	
	/**
	 * Construtor do enum
	 * @param descricao
	 */
	private StatusServico(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Get do atributo descrição
	 * @return
	 */
	public String getDescricao() {
		return descricao;
	}
	
	
}
