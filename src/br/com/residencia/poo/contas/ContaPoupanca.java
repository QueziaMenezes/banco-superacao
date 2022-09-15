package br.com.residencia.poo.contas;

import br.com.residencia.poo.principal.*;

public class ContaPoupanca extends Conta {
	
	@Override
	public String toString() {
		return "ContaPoupanca [taxaRendimento=" + taxaRendimento + ", tipoConta=" + tipoConta + ", agencia=" + agencia
				+ ", numero=" + numero + ", saldo=" + saldo + ", cpf=" + cpf + "]";
	}

	protected double taxaRendimento = 0.01575;
	
	public ContaPoupanca() {
		super();
	}

	public ContaPoupanca(String tipoConta, Integer agencia, Integer numero, Double saldo, String cpf) {
		super(tipoConta, agencia, numero, saldo, cpf);
	}
	

	@Override
	public void sacar(double valor) {
		if (valor < saldo) {
			if (valor >= 0) {
				this.saldo -= valor;
				// Usamos printf para limitar as casas decimais
				System.out.println("\nOperação realizada com sucesso!\n");
				System.out.printf("Valor sacado: R$%.2f", valor, "\n");
				System.out.printf("Saldo atual: R$%.2f", this.saldo, "\n");
				// LeituraEscrita.comprovanteSaque(funcionario, valor);
			} else {
				System.out.println("Saldo insuficiente.");
			}
		} else {
			System.out.println("Saldo insuficiente.");
		}
	}
	

	@Override
	public void depositar(double valor) {
		if (valor > 0) {
			this.saldo += valor;
			System.out.println("\nOperação realizada com sucesso!\n");
			System.out.printf("Valor depositado: R$%.2f", valor, "\n");
			System.out.printf("Saldo atual: R$%.2f", this.saldo, "\n");
		} else {
			System.out.println("Valor minimo.");
		}
	}
	
	
	@Override
	public void transferir(Conta destino, double valor) {
		if (this.saldo >= valor) {
			if (valor >= 0) {
				this.saldo -= valor;
				destino.saldo += valor;
				// Usamos printf para limitar as casas decimais
				System.out.println("\nOperação realizada com sucesso!\n");
				System.out.printf("Valor transferido: R$%.2f", valor, "\n");
				System.out.printf("Saldo atual: R$%.2f", this.saldo, "\n");
			} else {
				System.out.println("Valor insuficiente.");
			}
		} else {
			System.out.println("Valor insuficiente.");
		}
	}

	@Override
	public void verificarSaldo() {
		System.out.printf("R$ %.2f%n", this.saldo);
	}
	

	public String getTipo() {
		return "Conta Poupança";
	}
	

	public void poupazero() {
		double rendimento;
			
		System.out.print("Informe o numero de dias: ");
		int numeroDias = Principal.sc.nextInt();

		System.out.print("Informe o valor: ");
		double valor = Principal.sc.nextDouble();

		rendimento = (this.taxaRendimento / 365 * numeroDias) * valor;
		System.out.println("\nOperação realizada com sucesso!\n");
		System.out.printf("Valor investido: R$ %.2f%n", valor);
		System.out.printf("Valor do rendimento: R$ %.2f%n", rendimento);
		System.out.print("Valor total ao fim dos " + numeroDias + " dias:");
		System.out.printf(" R$ %.2f", (valor + rendimento));
		System.out.println();

	}

	// cálculo da previsao de rendimento mediante um valor qualquer
	public void previsaoDeRendimento(double valor, int dias) {
		double valorRendimento = (valor * 0.2) * dias;
		System.out.printf("\nO rendimento estimado daqui a " + dias + " dias será de: R$%.2f", valorRendimento);
		System.out.printf("\nO valor total será de: R$%.2f", valorRendimento + valor);
	}
}