package br.com.residencia.poo.enums;

public enum TipoPessoa {

	CLIENTE("Cliente", 0),
	OPERADOR_CAIXA("OperadorCaixa", 0),
	GERENTE("Gerente", 1), 
	DIRETOR("Diretor", 2),
	PRESIDENTE("Presidente", 3);
	
	
	private final Integer idTipoPessoa;
	private final String tipoPessoa;

	TipoPessoa(String tipoPessoa, Integer idTipoPessoa) {
		this.tipoPessoa = tipoPessoa;
		this.idTipoPessoa = idTipoPessoa;
	}

	public String getTipoPessoa() {
		return this.tipoPessoa;
	}

	public Integer getIdTipoPessoa() {
		return this.idTipoPessoa;
	}
	
}
