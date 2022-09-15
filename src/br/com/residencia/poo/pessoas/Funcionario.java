package br.com.residencia.poo.pessoas;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import br.com.residencia.poo.usuarios.Cliente;

public class Funcionario extends Cliente implements Comparable<Funcionario> {
	
	protected Integer agencia;
	protected Integer numero;
	protected Double salario;
	

	public static Map<String, Funcionario> mapaFuncionarios = new HashMap<>();
	public static TreeMap<String, Funcionario> OrdenaFuncionarios = new TreeMap<>();

	public Funcionario() {
	}

	public Funcionario(String tipoPessoa, String nome, String cpf, String senha, Integer agencia, Integer numero, Double salario) {
		super();
		this.nome = nome;
		this.tipoPessoa = tipoPessoa;
		this.cpf = cpf;
		this.senha = senha;
		this.agencia = agencia;
		this.numero = numero;
		this.salario = salario;
	}

	@Override
	public int compareTo(Funcionario outroFuncionario) {
		return this.nome.compareTo(outroFuncionario.getNome());
	}
	
	public Integer getAgencia() {
		return agencia;
	}

	public void setAgencia(Integer agencia) {
		this.agencia = agencia;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public static void setOrdenaFuncionarios(TreeMap<String, Funcionario> ordenaFuncionarios) {
		OrdenaFuncionarios = ordenaFuncionarios;
	}

	public String relatorioInformacoes() {
		return "Nome: " + this.nome + "\t CPF: " + this.cpf + "\tAgencia: " + this.agencia;
	}
}
