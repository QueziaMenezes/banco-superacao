package br.com.residencia.poo.contas;

import br.com.residencia.poo.tributos.SeguroDeVida;
import br.com.residencia.poo.tributos.Tributo;

public class ContaCorrente extends Conta implements Tributo, SeguroDeVida  {
	protected Integer totalSaques = 0;
	protected Integer totalDepositos = 0;
	protected Integer totalTransferencias = 0;
	protected Integer totalSeguroDeVida = 0;
	protected Double totalTributado = 0.0;
	protected double apolice;
	
	
	public ContaCorrente() {
		super();
	}

	public ContaCorrente(String tipoConta, Integer agencia, Integer numero, Double saldo, String cpf) {
		super(tipoConta, agencia, numero, saldo, cpf);
	}
	
	public Integer getTotalSaques() {
		return this.totalSaques;
	}
	
	public Integer getTotalDepositos() {
		return this.totalDepositos;
	}	
	
	public Integer getTotalTransferencias() {
		return this.totalTransferencias;
	}
	
	public Integer getTotalSeguroDeVida() {
		return this.totalTransferencias;
	}

	public Double getTotalTributado() {
		return totalTributado;
	}

	public double getApolice() {
		return apolice;
	}

	public void setApolice(double apolice) {
		this.apolice = apolice;
	}
	
	@Override	
	public String getTipo() {
		return "Conta Corrente";
	}

	@Override
	public void sacar(double valor) {
		double valorTributado = tributarSaque(valor);
		if (valorTributado < saldo && this.saldo - valorTributado >= 0) {
			this.saldo -= valorTributado;
			this.totalTributado += Tributo.SAQUE;
			System.out.println("\nOperação realizada com sucesso!\n");
			imprimeLinhaHorizontal();
			System.out.printf("\nValor sacado: R$%.2f", valor, "\n");
			System.out.printf("\nTaxa para saque: R$%.2f", Tributo.SAQUE, "\n");
			++totalDepositos;
		} else {
			System.out.println("Saldo insuficiente!");
		}
	}
	
	@Override
	public void depositar(double valor) {
		double valorTributado = tributarDeposito(valor);
		if (valorTributado > 0) {
			this.saldo += valorTributado;
			this.totalTributado += Tributo.DEPOSITO;
			System.out.println("\nOperação realizada com sucesso!\n");
			imprimeLinhaHorizontal();
			System.out.printf("\nValor depositado: R$%.2f", valor, "\n");
			System.out.printf("\nTaxa para depositar: R$%.2f", Tributo.DEPOSITO, "\n");
			++totalSaques;
		} else {
			System.out.println("Valor minimo!");
		}
	}
	
	
	@Override
	public void transferir(Conta destino, double valor) {
		double valorTributado = tributarTransferencia(valor);
		if (this.saldo >= valorTributado && (this.saldo - valorTributado >= 0)) {
			destino.saldo += valorTributado;
			this.saldo -= valorTributado;
			this.totalTributado += Tributo.TRANSFERENCIA;
			System.out.println("\nOperação realizada com sucesso!\n");
			imprimeLinhaHorizontal();
			System.out.printf("\nValor transferido: R$%.2f", valor, "\n");
			System.out.printf("\nTaxa para transferência: R$%.2f", Tributo.TRANSFERENCIA, "\n");
			++totalTransferencias;
		} else {
			System.out.println("\nValor insuficiente.");
		}
	}
	
	@Override
	public double tributarSaque(double valor) {
		return valor + Tributo.SAQUE;
	}
	
	@Override
	public double tributarDeposito(double valor) {
		return valor + Tributo.DEPOSITO;
	}
	
	@Override
	public double tributarTransferencia(double valor) {
		return valor + Tributo.TRANSFERENCIA;
	}
	
	public void seguroDeVida(double valor) {
		if (this.saldo >= valor * SeguroDeVida.SEGURODEVIDA) {
			System.out.println("\nOperação realizada com sucesso!\n");
			imprimeLinhaHorizontal();
			this.apolice = valor * SeguroDeVida.SEGURODEVIDA;
			this.totalTributado += SeguroDeVida.SEGURODEVIDA;
			this.saldo = this.saldo - this.apolice;
			++totalSeguroDeVida;
		} else {
			System.out.println("\nValor insuficiente!");
		}
	}
	
	@Override
	public String toString() {
		return "Conta Corrente\nNúmero da Agência = " + this.agencia + "\nNúmero da Conta = "
				+ this.numero + "\nSaldo = " + this.saldo + "\nCPF = " + this.cpf + "\n";
	}
}	