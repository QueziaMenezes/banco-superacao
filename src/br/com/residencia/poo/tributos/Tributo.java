package br.com.residencia.poo.tributos;

public interface Tributo {
	double SAQUE = 0.1, DEPOSITO = 0.1, TRANSFERENCIA = 0.2;

	double tributarSaque(double valor);

	double tributarDeposito(double valor);

	double tributarTransferencia(double valor);
}
