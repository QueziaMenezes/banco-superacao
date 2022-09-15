package br.com.residencia.poo.contas;

import br.com.residencia.poo.tributos.Tributo;

public class ContaCorrente extends Conta implements Tributo  {
	private Integer totalSaques = 0;
	private Integer totalDepositos = 0;
	private Integer totalTransferências  = 0;
	private Double totalTributado = 0.0;
	
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
	
	public Integer getTotalTransferências() {
		return this.totalTransferências;
	}

	public Double getTotalTributado() {
		return totalTributado;
	}
	
	public String getTipo() {
		return "Conta Corrente";
	}


	@Override
	public void sacar(double valor) {
		if (valor < saldo) {
			double valorTributado = tributarSaque(valor);
			if (this.saldo - valorTributado >= 0) {
				this.saldo -= valorTributado;
				this.totalTributado += Tributo.SAQUE;
				// Usamos printf para limitar as casas decimais
				System.out.println("\nOperação realizada com sucesso!\n");
				System.out.printf("Valor sacado: R$%.2f", valor, "\n");
				System.out.printf("Taxa para saque: R$%.2f", Tributo.SAQUE, "\n");
				System.out.printf("Saldo atual: R$%.2f", this.saldo, "\n");
				++totalDepositos;
			} else {
				System.out.println("Saldo insuficiente.");
			}
		} else {
			System.out.println("Saldo insuficiente.");
		}
	}
	

	@Override
	public void depositar(double valor) {
		double valorTributado = tributarDeposito(valor);
		if (valor > 0) {
			this.saldo += valorTributado;
			this.totalTributado += Tributo.DEPOSITO;
			System.out.println("\nOperação realizada com sucesso!\n");
			System.out.printf("Valor depositado: R$%.2f", valor, "\n");
			System.out.printf("Taxa para depositar: R$%.2f", Tributo.DEPOSITO, "\n");
			System.out.printf("Saldo atual: R$%.2f", this.saldo, "\n");
			++totalSaques;
		} else {
			System.out.println("Valor minimo.");
		}
	}
	
	
	@Override
	public void transferir(Conta destino, double valor) {
		double valorTributado = tributarTransferencia(valor);
		if (this.saldo >= valor) {
			if (this.saldo - valorTributado >= 0) {
				this.saldo -= valorTributado;
				destino.saldo += valorTributado;
				this.totalTributado += Tributo.TRANSFERENCIA;
				// Usamos printf para limitar as casas decimais
				System.out.println("\nOperação realizada com sucesso!\n");
				System.out.printf("Valor transferido: R$%.2f", valor, "\n");
				System.out.printf("Taxa para transferência: R$%.2f", Tributo.TRANSFERENCIA, "\n");
				System.out.printf("Saldo atual: R$%.2f", this.saldo, "\n");
				++totalTransferências;
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
	
	@Override
	public String toString() {
		return "Conta Corrente\tNúmero da Agência = " + this.agencia + "\tNúmero da Conta = "
				+ this.numero + "\tSaldo = " + this.saldo + "\tCPF = " + this.cpf + "\n";
	}
}