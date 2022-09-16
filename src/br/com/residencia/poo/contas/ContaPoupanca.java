package br.com.residencia.poo.contas;

import br.com.residencia.poo.principal.*;
import br.com.residencia.poo.tributos.Taxa;

public class ContaPoupanca extends Conta implements Taxa {
	double rendimento;
	
	@Override
	public String toString() {
		return "ContaPoupanca [taxaRendimento=" + taxaRendimento + ", tipoConta=" + tipoConta + ", agencia=" + agencia
				+ ", numero=" + numero + ", saldo=" + saldo + ", cpf=" + cpf + "]";
	}

	protected double taxaRendimento = 0.03;
	
	public ContaPoupanca() {
		super();
	}

	public ContaPoupanca(String tipoConta, Integer agencia, Integer numero, Double saldo, String cpf) {
		super(tipoConta, agencia, numero, saldo, cpf);
	}
	
	@Override
	public String getTipo() {
		return "Conta Poupança";
	}
	
	@Override
	public void sacar(double valor) {
		if (valor < saldo && valor >= 0) {
			this.saldo -= valor;
			System.out.println("\nOperação realizada com sucesso!\n");
			imprimeLinhaHorizontal();
			System.out.printf("\nValor sacado: R$%.2f", valor, "\n");
			System.out.printf("\nSaldo atual: R$%.2f", this.saldo, "\n");
		} else {
			System.out.println("Saldo insuficiente.");
		}
	}

	@Override
	public void depositar(double valor) {
		if (valor > 0) {
			this.saldo += valor;
			System.out.println("\nOperação realizada com sucesso!\n");
			imprimeLinhaHorizontal();
			System.out.printf("\nValor depositado: R$%.2f", valor, "\n");
			System.out.printf("\nSaldo atual: R$%.2f", this.saldo, "\n");
		} else {
			System.out.println("Valor minimo.");
		}
	}
	
	@Override
	public void transferir(Conta destino, double valor) {
		if (this.saldo >= valor && valor >= 0) {
			destino.saldo += valor;	
			this.saldo -= valor;
			System.out.println("\nOperação realizada com sucesso!\n");
			imprimeLinhaHorizontal();
			System.out.printf("\nValor transferido: R$%.2f", valor, "\n");
		} else {
			System.out.println("Valor insuficiente.");
		}
	}

	@Override
	public void verificarSaldo() {
		System.out.printf("R$ %.2f%n", this.saldo);
	}
	
	public void poupancaZero() {
		double soma;
		
		System.out.println("\nOperação realizada com sucesso!\n");
		imprimeLinhaHorizontal();
		
		System.out.print("Digite o numero de dias: ");
		int numeroDias = Principal.sc.nextInt();

		System.out.print("Digite o valor: ");
		double valor = Principal.sc.nextDouble();

		this.rendimento = (Taxa.RENDIMENTO / 365 * numeroDias) * valor;
		soma = valor + this.rendimento;
		
		System.out.println("\nOperação realizada com sucesso!\n");
		System.out.printf("\nValor investido: R$ %.2f%n", valor);
		System.out.printf("\nValor do rendimento: R$ %.2f%n", this.rendimento);
		System.out.print("\nValor total ao fim dos " + numeroDias + " dias:");
		System.out.printf(" R$ %.2f", soma);
	}
	
	public void previsaoDeRendimento(double valor, int dias) {
		System.out.println("\nOperação realizada com sucesso!\n");
		imprimeLinhaHorizontal();
		double valorRendimento = (valor * 0.2) * dias;
		System.out.printf("\nO rendimento estimado daqui a " + dias + " dias será de: R$%.2f", valorRendimento);
		System.out.printf("\nO valor total será de: R$%.2f", valorRendimento + valor);
	}
}