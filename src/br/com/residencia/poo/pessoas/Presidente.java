package br.com.residencia.poo.pessoas;

public class Presidente extends Funcionario {

	public Presidente() {
		super();
	}

	public Presidente(String tipoPessoa, String nome, String cpf, String senha, Integer agencia, Integer numeroConta, Double salario) {
		super(tipoPessoa, nome, cpf, senha, agencia, numeroConta, salario);
	}
}
