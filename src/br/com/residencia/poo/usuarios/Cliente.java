package br.com.residencia.poo.usuarios;

public class Cliente extends Pessoa {
	public Cliente() {
	}

	public Cliente(String tipoPessoa, String nome, String cpf, String senha) {
		this.nome = nome;
		this.cpf = cpf;
		this.senha = senha;
	}
}
