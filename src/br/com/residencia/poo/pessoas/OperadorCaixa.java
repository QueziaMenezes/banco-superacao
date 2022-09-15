package br.com.residencia.poo.pessoas;

public class OperadorCaixa extends Funcionario {
	public OperadorCaixa() {
		super();
	}

	public OperadorCaixa(String tipoPessoa, String nome, String cpf, String senha, Integer agencia, Integer numeroConta, Double salario) {
		super(tipoPessoa, nome, cpf, senha, agencia, numeroConta, salario);
	}
}
