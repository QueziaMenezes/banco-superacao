package br.com.residencia.poo.enums;

public enum TipoPessoa {

	GERENTE("Gerente", 1), 
	DIRETOR("Diretor", 2),
	PRESIDENTE("Presidente", 3),
	OPERADOR_CAIXA("Operador de caixa", 4);
	
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
