package br.com.residencia.poo.IO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.residencia.poo.contas.Conta;
import br.com.residencia.poo.contas.ContaCorrente;
import br.com.residencia.poo.contas.ContaPoupanca;
import br.com.residencia.poo.enums.TipoConta;
import br.com.residencia.poo.enums.TipoPessoa;
import br.com.residencia.poo.pessoas.Diretor;
import br.com.residencia.poo.pessoas.Funcionario;
import br.com.residencia.poo.pessoas.Gerente;
import br.com.residencia.poo.pessoas.OperadorCaixa;
import br.com.residencia.poo.tributos.Tributo;

public class LeituraEscrita {
	
	static final String PATH_BASICO = "./temp/";
	static final String EXTENSAO = ".txt";

	public static void leitor(String path) throws IOException {
		BufferedReader buffRead = new BufferedReader(new FileReader(PATH_BASICO + path + EXTENSAO));

		String linha = "";

		while (true) {
			linha = buffRead.readLine();
			if (linha != null) {
				String[] dados = linha.split(";");	
				if (dados[0].equalsIgnoreCase(TipoConta.CONTA_CORRENTE.getTipoConta())) {
					ContaCorrente cc = new ContaCorrente(dados[0], Integer.parseInt(dados[1]),
							Integer.parseInt(dados[2]), Double.parseDouble(dados[3]), dados[4]);
					Conta.mapaContas.put(dados[4], cc);					
				} else if (dados[0].equalsIgnoreCase(TipoConta.CONTA_POUPANCA.getTipoConta())) {
					ContaPoupanca cp = new ContaPoupanca(dados[0], Integer.parseInt(dados[1]),
							Integer.parseInt(dados[2]), Double.parseDouble(dados[3]), dados[4]);
					Conta.mapaContas.put(dados[4], cp);
				} else if (dados[0].equalsIgnoreCase(TipoPessoa.GERENTE.getTipoPessoa())) {
					Gerente gerente = new Gerente(dados[0], dados[1], dados[2], dados[3], Integer.parseInt(dados[4]),
							Integer.parseInt(dados[5]), Double.parseDouble(dados[6]), Integer.parseInt(dados[7]));
					Funcionario.mapaFuncionarios.put(dados[2], gerente);
					Funcionario.OrdenaFuncionarios.put(dados[1], gerente);
				} else if (dados[0].equalsIgnoreCase(TipoPessoa.DIRETOR.getTipoPessoa())) {
					Diretor diretor = new Diretor(dados[0], dados[1], dados[2], dados[3], Integer.parseInt(dados[4]),
							Integer.parseInt(dados[5]), Double.parseDouble(dados[6]));
					Funcionario.mapaFuncionarios.put(dados[2], diretor);
					Funcionario.OrdenaFuncionarios.put(dados[1], diretor);
				} else if (dados[0].equalsIgnoreCase(TipoPessoa.PRESIDENTE.getTipoPessoa())) {
					OperadorCaixa operadorCaixa = new OperadorCaixa(dados[0], dados[1], dados[2], dados[3], Integer.parseInt(dados[4]),
							Integer.parseInt(dados[5]), Double.parseDouble(dados[6]));
					Funcionario.mapaFuncionarios.put(dados[2], operadorCaixa);
					Funcionario.OrdenaFuncionarios.put(dados[1], operadorCaixa);
				}
			} else {
				break;
			}
		}
		buffRead.close();
	}

	public static void comprovanteSaque(Conta conta, double valorSaque) throws IOException {
		String nomeArquivo = conta.getCpf() + "_" + conta.getAgencia() + "_" + conta.getNumero()
				+ "_transacoes";
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(PATH_BASICO + nomeArquivo + EXTENSAO, true));

		String linha = "*************** SAQUE ***************";
		buffWrite.append(linha + "\n");

		linha = "CPF: " + conta.getCpf();
		buffWrite.append(linha + "\n");

		linha = "Agência: " + conta.getAgencia();
		buffWrite.append(linha + "\n");

		linha = "Conta: " + conta.getNumero();
		buffWrite.append(linha + "\n");

		linha = "Valor: R$ " + valorSaque;
		buffWrite.append(linha + "\n");

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		linha = "Operação realizada em: " + simpleDateFormat.format(date);
		buffWrite.append(linha + "\n");

		linha = "*************** FIM DO SAQUE ***************";
		buffWrite.append(linha + "\n\n");

		buffWrite.close();

	}

	public static void comprovanteDeposito(Conta conta, double valorDeposito) throws IOException {
		String nomeArquivo = conta.getCpf() + "_" + conta.getAgencia() + "_" + conta.getNumero()
				+ "_transacoes";

		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(PATH_BASICO + nomeArquivo + EXTENSAO, true));

		String linha = "*************** DEPÓSITO ***************";
		buffWrite.append(linha + "\n");

		linha = "CPF: " + conta.getCpf();
		buffWrite.append(linha + "\n");

		linha = "Agência: " + conta.getAgencia();
		buffWrite.append(linha + "\n");

		linha = "Conta: " + conta.getNumero();
		buffWrite.append(linha + "\n");

		linha = "Valor: R$ " + valorDeposito;
		buffWrite.append(linha + "\n");

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		linha = "Operação realizada em: " + simpleDateFormat.format(date);
		buffWrite.append(linha + "\n");

		linha = "*************** FIM DO DEPÓSITO ***************";
		buffWrite.append(linha + "\n\n");

		buffWrite.close();

	}

	public static void comprovanteTransferencia(Conta conta, double valorTransferencia, Conta destino)
			throws IOException {
		String nomeArquivo = conta.getCpf() + "_" + conta.getAgencia() + "_" + conta.getNumero()
				+ "_transacoes";

		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(PATH_BASICO + nomeArquivo + EXTENSAO, true));

		String linha = "*************** TRANSFERÊNCIA REALIZADA ***************";
		buffWrite.append(linha + "\n\n");

		linha = "*********** DADOS DO REMETENTE **********";
		buffWrite.append(linha + "\n");

		linha = "CPF: " + conta.getCpf();
		buffWrite.append(linha + "\n");

		linha = "Agência : " + conta.getAgencia();
		buffWrite.append(linha + "\n");

		linha = "Conta: " + conta.getNumero();
		buffWrite.append(linha + "\n");

		linha = "********** DADOS DO DESTINATÁRIO ***********";
		buffWrite.append(linha + "\n");

		linha = "CPF: " + destino.getCpf();
		buffWrite.append(linha + "\n");

		linha = "Agência: " + destino.getAgencia();
		buffWrite.append(linha + "\n");

		linha = "Conta: " + destino.getNumero();
		buffWrite.append(linha + "\n");

		linha = "***********************************************";
		buffWrite.append(linha + "\n");

		linha = "Valor: R$ " + valorTransferencia;
		buffWrite.append(linha + "\n");

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		linha = "Operação realizada em: " + simpleDateFormat.format(date);
		buffWrite.append(linha + "\n");

		linha = "*************** FIM DA TRANSFERÊNCIA ***************";
		buffWrite.append(linha + "\n\n");

		buffWrite.close();

//		// REGISTRAR DADOS NO COMPROVANTE DO DESTINATARIO
//		nomeArquivo = destino.getCpf() + "_" + destino.getNumeroAgencia() + "_" + destino.getNumeroConta()
//				+ "_transacoes";
//
//		buffWrite = new BufferedWriter(new FileWriter(PATH_BASICO + nomeArquivo + EXTENSAO, true));
//
//		linha = "*************** TRANSFERÊNCIA RECEBIDA ***************";
//		buffWrite.append(linha + "\n\n");
//
//		linha = "*********** DADOS DO REMETENTE **********";
//		buffWrite.append(linha + "\n");
//
//		linha = "CPF: " + conta.getCpf();
//		buffWrite.append(linha + "\n");
//
//		linha = "Agência : " + conta.getNumeroAgencia();
//		buffWrite.append(linha + "\n");
//
//		linha = "Conta: " + conta.getNumeroConta();
//		buffWrite.append(linha + "\n");
//
//		linha = "********** DADOS DO DESTINATÁRIO ***********";
//		buffWrite.append(linha + "\n");
//
//		linha = "CPF: " + destino.getCpf();
//		buffWrite.append(linha + "\n");
//
//		linha = "Agência: " + destino.getNumeroAgencia();
//		buffWrite.append(linha + "\n");
//
//		linha = "Conta: " + destino.getNumeroConta();
//		buffWrite.append(linha + "\n");
//
//		linha = "***********************************************";
//		buffWrite.append(linha + "\n");
//
//		linha = "Valor: R$ " + valorTransferencia;
//		buffWrite.append(linha + "\n");
//
//		simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//		date = new Date();
//		linha = "Operação realizada em: " + simpleDateFormat.format(date);
//		buffWrite.append(linha + "\n");
//
//		linha = "*************** FIM DA TRANSFERÊNCIA ***************";
//		buffWrite.append(linha + "\n");

		buffWrite.close();
	}

	public static void relatorioTributacaoContaCorrente(Conta conta) throws IOException {
		String nomeArquivo = conta.getCpf() + "_" + conta.getAgencia() + "_" + conta.getNumero()
				+ "_tributacoes";

		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(PATH_BASICO + nomeArquivo + EXTENSAO, true));

		String linha = "*************** TOTAL DE TRIBUTAÇÕES ***************";
		buffWrite.append(linha + "\n\n");
		DecimalFormat df = new DecimalFormat("0.00");
		linha = "Total gasto com transações = R$ " + df.format(((ContaCorrente) conta).getTotalTributado());
		buffWrite.append(linha + "\n");

		linha = "Taxa para saque = R$ " + df.format(Tributo.SAQUE);
		buffWrite.append(linha + "\n");

		linha = "Total de saques realizados = " + ((ContaCorrente) conta).getTotalSaques();
		buffWrite.append(linha + "\n\n");

		linha = "Taxa para deposito = R$ " + df.format(Tributo.DEPOSITO);
		buffWrite.append(linha + "\n");

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		linha = "Operação realizada em: " + simpleDateFormat.format(date);
		buffWrite.append(linha + "\n");

		linha = "****************************************************";
		buffWrite.append(linha + "\n\n");

		buffWrite.close();
	}

	public static void comprovanteSaldo(Conta conta) throws IOException {
		String nomeArquivo = conta.getCpf() + "_" + conta.getAgencia() + "_" + conta.getNumero()
				+ "_comprovanteSaldo";

		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(PATH_BASICO + nomeArquivo + EXTENSAO, true));

		String linha = "*************** SALDO ***************";
		buffWrite.append(linha + "\n");

		linha = "Tipo: " + conta.getTipoConta();
		buffWrite.append(linha + "\n");

		linha = "Agência: " + conta.getAgencia();
		buffWrite.append(linha + "\n");

		linha = "Conta: " + conta.getNumero();
		buffWrite.append(linha + "\n");

		linha = "Saldo: R$ " + conta.getSaldo();
		buffWrite.append(linha + "\n");

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		linha = "Operação realizada em: " + simpleDateFormat.format(date);
		buffWrite.append(linha + "\n");

		linha = "*************** FIM ***************";
		buffWrite.append(linha + "\n");

		buffWrite.close();
	}
	
}
