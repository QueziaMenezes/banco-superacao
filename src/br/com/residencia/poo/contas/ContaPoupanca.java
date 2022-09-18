package br.com.residencia.poo.contas;

import br.com.residencia.poo.tributos.SeguroDeVida;

public class ContaPoupanca extends Conta implements SeguroDeVida {
	protected double rendimento;
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
		if (valor <= 0) {
			System.out.println("Valor inválido!");
		} else if (this.saldo > 0 && this.saldo >= valor) {
			this.saldo = this.saldo - valor;
			System.out.println("\nOperação realizada com sucesso!\n");
			imprimeLinhaHorizontal();
			System.out.printf("\nValor sacado: R$%.2f", valor, "\n");
		} else {
			System.out.println("\nValor insuficiente!");
		}
	}

	@Override
	public void depositar(double valor) {
		if (valor > 0) {
			this.saldo = this.saldo + valor;
			System.out.println("\nOperação realizada com sucesso!\n");
			imprimeLinhaHorizontal();
			System.out.printf("\nValor depositado: R$%.2f", valor, "\n");
		} else {
			System.out.println("Valor minimo!");
		}
	}
	
	@Override
	public void transferir(Conta destino, double valor) {
		if (this.saldo >= valor) {
			destino.saldo = destino.saldo + valor;	
			this.saldo = this.saldo - valor;
			System.out.println("\nOperação realizada com sucesso!\n");
			imprimeLinhaHorizontal();
			System.out.printf("\nValor transferido: R$%.2f", valor, "\n");
		} else {
			System.out.println("Valor insuficiente!");
		}
	}
	
	public double previsaoDeRendimento(double valor, int dias) {
		System.out.println("\nOperação realizada com sucesso!\n");
		imprimeLinhaHorizontal();
		double valorRendimento = (valor * 0.06) * dias;
		this.rendimento = valorRendimento;
		System.out.printf("\nValor investido: R$ %.2f%n", valor);
		System.out.printf("\nValor total ao fim dos " + dias + " dias será de: R$%.2f", valorRendimento);
		System.out.printf("\nO valor total será de: R$%.2f", valorRendimento + valor);
		return valorRendimento;
	}
	
	@Override
	public String toString() {
		return "Conta Poupanca\nNúmero da Agência = " + this.agencia + "\nNúmero da Conta = "
				+ this.numero + "\nSaldo = " + this.saldo + "\nCPF = " + this.cpf + "\n";
	}
}