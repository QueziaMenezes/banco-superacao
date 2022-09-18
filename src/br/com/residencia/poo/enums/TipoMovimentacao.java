package br.com.residencia.poo.enums;

public enum TipoMovimentacao {
	SAQUE("Saque", 1),
	DEPOSITO("Deposito", 2),
	TRANSFERENCIA("Transferência", 3), 
	ABERTURA_CONTA("Abertura de conta", 4), 
	APLICACAO("Aplicação", 5),
	RENDIMENTO("Rendimento", 6);

	private Integer idTipoMovimentacao;
	private String tipoMovimentacao;
		
	private TipoMovimentacao(String tipoMovimentacao, int idTipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
		this.idTipoMovimentacao = idTipoMovimentacao;
	}
	
	public Integer getIdTipoMovimentacao() {
		return this.idTipoMovimentacao;
	}
	
	public String getTipoMovimentacao() {
		return this.tipoMovimentacao;
	}
}
