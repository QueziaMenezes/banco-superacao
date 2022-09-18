package br.com.residencia.poo.usuarios;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Cliente extends Pessoa {
	public Cliente() {
	}

	public Cliente(String tipoPessoa, String nome, String cpf, String senha) {
		this.tipoPessoa = tipoPessoa;
		this.nome = nome;
		this.cpf = cpf;
		this.senha = senha;
	}
	
	public static Map<String, Cliente> mapaCliente = new HashMap<>();
	public static TreeMap<String, Cliente> OrdenaCliente = new TreeMap<>();
	
	public static void setOrdenaCliente(TreeMap<String, Cliente> ordenaCliente) {
		OrdenaCliente = ordenaCliente;
	}
	
	public String relatorioInformacoes() {
		return "Nome: " + this.nome + "\t CPF: " + this.cpf;
	}
}
