package br.com.residencia.poo.enums;

public enum TipoConta {
	CONTA_CORRENTE("Corrente", 1),
	CONTA_POUPANCA("Poupanca", 2);
	
	private final String tipoConta;
	private final Integer idTipoConta;

	TipoConta(String tipoConta, Integer idTipoConta) {
		this.tipoConta = tipoConta;
		this.idTipoConta = idTipoConta;
	}

	public String getTipoConta() {
		return this.tipoConta;
	}

	public Integer getIdTipoConta() {
		return this.idTipoConta;
	}
}
