package br.com.residencia.poo.enums;

public enum TipoMovimentacao {
	
	SAQUE("Saque"),
	DEPOSITO("Deposito"),
	TRANSFERENCIA("Transferência"), 
	ABERTURA_CONTA("Abertura de conta"), 
	APLICACAO("Aplicação"),
	RENDIMENTO("Rendimento");
	
	private String valor;
		
	private TipoMovimentacao(String valor) {
		this.valor = valor;
	}
	
	public String getValor() {
		return this.valor;
	}
}
