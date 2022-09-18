package br.com.residencia.poo.menus;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.residencia.poo.IO.*;
import br.com.residencia.poo.contas.*;
import br.com.residencia.poo.enums.*;
import br.com.residencia.poo.pessoas.*;
import br.com.residencia.poo.principal.*;
import br.com.residencia.poo.tributos.SeguroDeVida;
import br.com.residencia.poo.tributos.Tributo;
import br.com.residencia.poo.usuarios.Cliente;

public class MenuPrincipal {

	protected double inputValor;
	protected static String inputCpf;
	protected int operacao;

	public static void menuPrincipal(Funcionario funcionario, Conta conta) throws IOException {
		Principal principal = new Principal();
		String nome = funcionario.getNome();
		
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date = new Date();
			
			System.out.println("\nTipo: " + conta.getTipo());
			System.out.println("Operação realizada em: " + simpleDateFormat.format(date));
			principal.imprimeLinhaHorizontal();
			System.out.println("Digite o número correspondente a operação desejada.");
			System.out.println("Escolha uma das opções: ");
			System.out.println("1 - Saque.");
			System.out.println("2 - Depósito.");
			System.out.println("3 - Transferência.");
			System.out.println("4 - Consulta Saldo.");
			if (conta.getTipoConta().equalsIgnoreCase(TipoConta.CONTA_CORRENTE.getTipoConta())) {
				System.out.println("5 - Contratar Seguro de Vida.");
			}
			System.out.println("6 - Relatório.");
			if (conta.getTipoConta().equalsIgnoreCase(TipoConta.CONTA_CORRENTE.getTipoConta())) {
				System.out.println("7 - Tributação conta corrente.");
			} else {
				System.out.println("7 - Simulação de rendimento.");
			}
			System.out.println("0 - Sair");
			System.out.print("Digite a opção desejada: ");
			int opcao = Principal.sc.nextInt();
			Double inputValor;
			int enter;
			switch (opcao) {
			case 1:
				principal.imprimeLinhaHorizontal();
				System.out.print("Digite o valor que deseja sacar: ");
				inputValor = Double.parseDouble(Principal.sc.next());
				conta.sacar(inputValor);
				System.out.printf("\nSeu saldo é: R$%.2f", conta.getSaldo());
				LeituraEscrita.comprovanteSaque(conta, inputValor, nome);
				System.out.println("\nDigite 1 para continuar.");
				enter = Principal.sc.nextInt();
				principal.limpar();
				break;
			case 2:
				principal.imprimeLinhaHorizontal();
				System.out.print("Digite o valor que deseja depositar: ");
				inputValor = Double.parseDouble(Principal.sc.next());
				conta.depositar(inputValor);
				System.out.printf("\nSeu saldo é: R$%.2f", conta.getSaldo());
				LeituraEscrita.comprovanteDeposito(conta, inputValor, nome);
				System.out.println("\nDigite 1 para continuar.");
				enter = Principal.sc.nextInt();
				principal.limpar();
				break;
			case 3:
				principal.imprimeLinhaHorizontal();
				System.out.print("Digite o CPF da conta destino: ");
				String cpfDestino = Principal.sc.next();
				Conta contaDestino = (Conta) Conta.mapaContas.get(cpfDestino);

				if(contaDestino != null ) {
						System.out.print("Digite o valor a ser transferido: ");
						inputValor = Double.parseDouble(Principal.sc.next());
						conta.transferir(contaDestino, inputValor);
						System.out.printf("\nValor transferido foi de: R$ %.2f%n", inputValor);
						
						System.out.print("\nSaldo atual de " + nome + " ");
						System.out.printf("\nSaldo é: R$%.2f", conta.getSaldo());
						
						System.out.printf("\nSaldo é: R$%.2f", contaDestino.getSaldo());
						LeituraEscrita.comprovanteTransferencia(conta, nome, inputValor, contaDestino);
				} else {
					System.out.println("CPF inválida!");
				}
				System.out.println("\nDigite 1 para continuar.");
				enter = Principal.sc.nextInt();
				
				principal.limpar();
				break;
			case 4:
				principal.imprimeLinhaHorizontal();
				System.out.printf("Seu saldo é: R$%.2f", conta.getSaldo());
				System.out.println("\nDigite 1 para continuar.");
				enter = Principal.sc.nextInt();
				principal.limpar();
				LeituraEscrita.comprovanteSaldo(conta);
				break;
			case 5:
				principal.imprimeLinhaHorizontal();
				if (conta.getTipoConta().equalsIgnoreCase(TipoConta.CONTA_CORRENTE.getTipoConta())) {
					System.out.print("Digite o valor que deseja assegurar: ");
					inputValor = Double.parseDouble(Principal.sc.next());
					((ContaCorrente) conta).seguroDeVida(inputValor);
					LeituraEscrita.relatorioSeguroDeVida(conta);
				} else {
					System.out.printf("Não e uma Conta Corrente!");
				}
				System.out.println("\nDigite 1 para continuar.");
				enter = Principal.sc.nextInt();
				principal.limpar();
				break;
			case 6:
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
				principal.limpar();
				break;
			case 7:
				if (conta.getTipoConta().equalsIgnoreCase(TipoConta.CONTA_CORRENTE.getTipoConta())) {
					principal.imprimeLinhaHorizontal();
					System.out.println("\nTributação conta corrente.\n");
					System.out.printf("O total gasto com operações foi de R$%.2f", ((ContaCorrente) conta).getTotalTributado());
					System.out.printf("\nO valor cobrado para cada saque é de R$ %.2f", Tributo.SAQUE);
					System.out.printf("\nO valor cobrado para cada deposito é de R$ %.2f", Tributo.DEPOSITO);
					System.out.printf("\nO valor cobrado para cada transferência é de R$ %.2f", Tributo.TRANSFERENCIA);
					System.out.printf("\nO valor cobrado para cada seguro de vida é de R$ %.2f", SeguroDeVida.SEGURODEVIDA);
					principal.imprimeLinhaHorizontal();
					System.out.println("\nTotal de saques realizados: " + ((ContaCorrente) conta).getTotalSaques());
					System.out.println("\nTotal de depositos realizados: " + ((ContaCorrente) conta).getTotalDepositos());
					System.out.println("\nTotal de transferidos realizados: " + ((ContaCorrente) conta).getTotalTransferencias());
					System.out.println("\nTotal de seguros de vida realizados: " + ((ContaCorrente) conta).getTotalSeguroDeVida());
					LeituraEscrita.relatorioTributacaoContaCorrente(conta);
					System.out.println("\nDigite 1 para continuar.");
					enter = Principal.sc.nextInt();
				} else {
					int inputDias;
					principal. imprimeLinhaHorizontal();
					System.out.println("\nSimulação de rendimento.\n");
					System.out.println("Digite o valor para a simulação: ");
					inputValor = Double.parseDouble(Principal.sc.next());
					System.out.println("Digite o número de dias para a simulação: ");
					inputDias = Principal.sc.nextInt();
					double valorRendimento = ((ContaPoupanca) conta).previsaoDeRendimento(inputValor, inputDias);
					LeituraEscrita.relatorioRendimentoPoupanca(conta, inputValor, inputDias, valorRendimento);
					System.out.println("\nDigite 1 para continuar.");
					enter = Principal.sc.nextInt();
				}
				principal.limpar();
				break;
			case 0:
				principal.imprimeLinhaHorizontal();
				principal.limpar();
				principal.menuInterativo();
				break;
			default:
				System.out.println("Opção inválida!");
				principal.limpar();
			}

			menuPrincipal(funcionario, conta);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			menuPrincipal(funcionario, conta);
		}
	}
	

	public static void menuCliente(Cliente cliente, Conta conta) throws IOException {
		Principal principal = new Principal();
		String nome = cliente.getNome();
		
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date = new Date();
			
			System.out.println("\nTipo: " + conta.getTipo());
			System.out.println("Operação realizada em: " + simpleDateFormat.format(date));
			principal.imprimeLinhaHorizontal();
			System.out.println("Digite o número correspondente a operação desejada.");
			System.out.println("Escolha uma das opções: ");
			System.out.println("1 - Saque.");
			System.out.println("2 - Depósito.");
			System.out.println("3 - Transferência.");
			System.out.println("4 - Consulta Saldo.");
			if (conta.getTipoConta().equalsIgnoreCase(TipoConta.CONTA_CORRENTE.getTipoConta())) {
				System.out.println("5 - Contratar Seguro de Vida.");
			}
			if (conta.getTipoConta().equalsIgnoreCase(TipoConta.CONTA_CORRENTE.getTipoConta())) {
				System.out.println("6 - Tributação conta corrente.");
			} else {
				System.out.println("6 - Simulação de rendimento.");
			}
			System.out.println("0 - Sair");
			System.out.print("Digite a opção desejada: ");
			int opcao = Principal.sc.nextInt();
			Double inputValor;
			int enter;
			switch (opcao) {
			case 1:
				principal.imprimeLinhaHorizontal();
				System.out.print("Digite o valor que deseja sacar: ");
				inputValor = Double.parseDouble(Principal.sc.next());
				conta.sacar(inputValor);
				System.out.printf("\nSeu saldo é: R$%.2f", conta.getSaldo());
				LeituraEscrita.comprovanteSaque(conta, inputValor, nome);
				System.out.println("\nDigite 1 para continuar.");
				enter = Principal.sc.nextInt();
				principal.limpar();
				break;
			case 2:
				principal.imprimeLinhaHorizontal();
				System.out.print("Digite o valor que deseja depositar: ");
				inputValor = Double.parseDouble(Principal.sc.next());
				conta.depositar(inputValor);
				System.out.printf("\nSeu saldo é: R$%.2f", conta.getSaldo());
				LeituraEscrita.comprovanteDeposito(conta, inputValor, nome);
				System.out.println("\nDigite 1 para continuar.");
				enter = Principal.sc.nextInt();
				principal.limpar();
				break;
			case 3:
				principal.imprimeLinhaHorizontal();
				System.out.print("Digite o CPF da conta destino: ");
				String cpfDestino = Principal.sc.next();
				Conta contaDestino = (Conta) Conta.mapaContas.get(cpfDestino);

				if(conta.getCpf() != cpfDestino && contaDestino != null ) {
						System.out.print("Digite o valor a ser transferido: ");
						inputValor = Double.parseDouble(Principal.sc.next());
						conta.transferir(contaDestino, inputValor);
						System.out.printf("\nValor transferido foi de: R$ %.2f%n", inputValor);
						
						System.out.print("\nSaldo atual de " + nome + " ");
						System.out.printf("\nSaldo é: R$%.2f", conta.getSaldo());
						
						System.out.printf("\nSaldo do destinatario é: R$%.2f", contaDestino.getSaldo());
						LeituraEscrita.comprovanteTransferencia(conta, nome, inputValor, contaDestino);
				} else {
					System.out.println("CPF inválida!");
				}
				System.out.println("\nDigite 1 para continuar.");
				enter = Principal.sc.nextInt();
				
				principal.limpar();
				break;
			case 4:
				principal.imprimeLinhaHorizontal();
				System.out.printf("Seu saldo é: R$%.2f", conta.getSaldo());
				System.out.println("\nDigite 1 para continuar.");
				enter = Principal.sc.nextInt();
				principal.limpar();
				LeituraEscrita.comprovanteSaldo(conta);
				break;
			case 5:
				principal.imprimeLinhaHorizontal();
				if (conta.getTipoConta().equalsIgnoreCase(TipoConta.CONTA_CORRENTE.getTipoConta())) {
					System.out.print("Digite o valor que deseja assegurar: ");
					inputValor = Double.parseDouble(Principal.sc.next());
					((ContaCorrente) conta).seguroDeVida(inputValor);
					LeituraEscrita.relatorioSeguroDeVida(conta);
				} else {
					System.out.printf("Não e uma Conta Corrente!");
				}
				System.out.println("\nDigite 1 para continuar.");
				enter = Principal.sc.nextInt();
				principal.limpar();
				break;
			case 6:
				if (conta.getTipoConta().equalsIgnoreCase(TipoConta.CONTA_CORRENTE.getTipoConta())) {
					principal.imprimeLinhaHorizontal();
					System.out.println("\nTributação conta corrente.\n");
					System.out.printf("O total gasto com operações foi de R$%.2f", ((ContaCorrente) conta).getTotalTributado());
					System.out.printf("\nO valor cobrado para cada saque é de R$ %.2f", Tributo.SAQUE);
					System.out.printf("\nO valor cobrado para cada deposito é de R$ %.2f", Tributo.DEPOSITO);
					System.out.printf("\nO valor cobrado para cada transferência é de R$ %.2f", Tributo.TRANSFERENCIA);
					System.out.printf("\nO valor cobrado para cada seguro de vida é de R$ %.2f", SeguroDeVida.SEGURODEVIDA);
					principal.imprimeLinhaHorizontal();
					System.out.println("\nTotal de saques realizados: " + ((ContaCorrente) conta).getTotalSaques());
					System.out.println("\nTotal de depositos realizados: " + ((ContaCorrente) conta).getTotalDepositos());
					System.out.println("\nTotal de transferidos realizados: " + ((ContaCorrente) conta).getTotalTransferencias());
					System.out.println("\nTotal de seguros de vida realizados: " + ((ContaCorrente) conta).getTotalSeguroDeVida());
					LeituraEscrita.relatorioTributacaoContaCorrente(conta);
					System.out.println("\nDigite 1 para continuar.");
					enter = Principal.sc.nextInt();
				} else {
					int inputDias;
					principal. imprimeLinhaHorizontal();
					System.out.println("\nSimulação de rendimento.\n");
					System.out.println("Digite o valor para a simulação: ");
					inputValor = Double.parseDouble(Principal.sc.next());
					System.out.println("Digite o número de dias para a simulação: ");
					inputDias = Principal.sc.nextInt();
					double valorRendimento = ((ContaPoupanca) conta).previsaoDeRendimento(inputValor, inputDias);
					LeituraEscrita.relatorioRendimentoPoupanca(conta, inputValor, inputDias, valorRendimento);
					System.out.println("\nDigite 1 para continuar.");
					enter = Principal.sc.nextInt();
				}
				principal.limpar();
				break;
			case 0:
				principal.imprimeLinhaHorizontal();
				principal.limpar();
				principal.menuInterativo();
				break;
			default:
				System.out.println("Opção inválida!");
				principal.limpar();
			}

			menuCliente(cliente, conta);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			menuCliente(cliente, conta);
		}
	}
}