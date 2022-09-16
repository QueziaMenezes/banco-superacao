package br.com.residencia.poo.menus;

import java.io.IOException;

import br.com.residencia.poo.IO.*;
import br.com.residencia.poo.contas.*;
import br.com.residencia.poo.enums.*;
import br.com.residencia.poo.pessoas.*;
import br.com.residencia.poo.principal.*;

public class Menu {

	protected double inputValor;
	protected static String inputCpf;
	protected int operacao;

	public static void menuPrincipal(Funcionario funcionario, Conta conta) throws IOException {
		Principal principal = new Principal();

		try {
			principal.imprimeLinhaHorizontal();
			System.out.println("Digite o número correspondente a operação desejada:");
			System.out.println("Escolha uma das opções: ");
			System.out.println("1 - Saque");
			System.out.println("2 - Depósito");
			System.out.println("3 - Transferência");
			System.out.println("4 - Consulta Saldo");
			System.out.println("5 - Relatório");
			// System.out.println("6 - Simulação rendimento poupança");
			System.out.println("6 - Contratar Seguro de Vida");
			System.out.println("7 - Sair");
			System.out.print("Digite a opção desejada: ");
			int opcao = Principal.sc.nextInt();
			Double inputValor;
			switch (opcao) {
			case 1:
				principal.imprimeLinhaHorizontal();
				System.out.print("Digite o valor que deseja sacar: ");
				inputValor = Double.parseDouble(Principal.sc.next());
				conta.sacar(inputValor);
				LeituraEscrita.comprovanteSaque(conta, inputValor);
				break;
			case 2:
				principal.imprimeLinhaHorizontal();
				System.out.print("Digite o valor que deseja depositar: ");
				inputValor = Double.parseDouble(Principal.sc.next());
				conta.depositar(inputValor);
				LeituraEscrita.comprovanteDeposito(conta, inputValor);
				break;
			case 3:
				principal.imprimeLinhaHorizontal();
				System.out.print("Digite o CPF da conta destino: ");
				String cpfDestino = Principal.sc.next();
				Funcionario funcionarioDestino = (Funcionario) Funcionario.mapaFuncionarios.get(cpfDestino);
				Conta contaDestino = (Conta) Conta.mapaContas.get(cpfDestino);
				System.out.print("Digite o valor a ser transferido: ");
				inputValor = Double.parseDouble(Principal.sc.next());
				conta.transferir(contaDestino, inputValor);
				System.out.printf("Valor transferido foi de: R$ %.2f%n", inputValor);
				
				System.out.print("Saldo atual de " + funcionarioDestino.getNome() + " ");
				conta.verificarSaldo();	
				contaDestino.verificarSaldo();
				LeituraEscrita.comprovanteTransferencia(conta, funcionario, inputValor, contaDestino, funcionarioDestino);
				
				break;
			case 4:
				principal.imprimeLinhaHorizontal();
				System.out.printf("Seu saldo é: R$%.2f", conta.getSaldo());
				LeituraEscrita.comprovanteSaldo(conta);
				break;
			case 5:
				principal.imprimeLinhaHorizontal();
				if (funcionario.getTipoPessoa().equalsIgnoreCase(TipoPessoa.GERENTE.getTipoPessoa())) {
					MenuRelatorio.menuRelatorio(TipoPessoa.GERENTE.getIdTipoPessoa(), funcionario, conta);
				} else if (funcionario.getTipoPessoa().equalsIgnoreCase(TipoPessoa.DIRETOR.getTipoPessoa())) {
					MenuRelatorio.menuRelatorio(TipoPessoa.DIRETOR.getIdTipoPessoa(), funcionario, conta);
				} else if (funcionario.getTipoPessoa().equalsIgnoreCase(TipoPessoa.PRESIDENTE.getTipoPessoa())) {
					MenuRelatorio.menuRelatorio(TipoPessoa.PRESIDENTE.getIdTipoPessoa(), funcionario, conta);
				} else if (funcionario.getTipoPessoa().equalsIgnoreCase(TipoPessoa.OPERADOR_CAIXA.getTipoPessoa())) {
					MenuRelatorio.menuRelatorio(TipoPessoa.OPERADOR_CAIXA.getIdTipoPessoa(), funcionario, conta);
				}
				break;
			/*case 6:
				if (conta.getTipo() == "Conta Poupança") {
					((ContaPoupanca) conta).poupanca();
				} else {
					System.out.printf("Não e uma Conta Poupança");
				}
				break;*/
			case 6:
				System.out.print("Digite o valor que deseja assegurar: ");
				inputValor = Double.parseDouble(Principal.sc.next());
				if (conta.getSaldo() >= inputValor * 0.2) {
					((ContaCorrente) conta).seguroDeVida(inputValor);
					System.out.printf("Valor assegurado: R$ %.2f%n", inputValor);
					System.out.printf("\nValor da apólice: R$ %.2f%n", ((ContaCorrente) conta).getApolice());
				} else {
					System.out.println("Saldo insuficiente");
				}
				break;
			case 7:
				principal.imprimeLinhaHorizontal();
				principal.menuInterativo();
				break;
			default:
				System.out.println("Opção inválida!");
			}

			menuPrincipal(funcionario, conta);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			menuPrincipal(funcionario, conta);
		}
	}
}
